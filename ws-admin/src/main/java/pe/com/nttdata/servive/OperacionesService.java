package pe.com.nttdata.servive;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.nttdata.client.CustomerClientInf;
import pe.com.nttdata.client.HistoricoClientInf;
import pe.com.nttdata.client.ProductClientInf;
import pe.com.nttdata.client.TypeCustomerClientInf;
import pe.com.nttdata.model.Customer;
import pe.com.nttdata.model.CustomerResponse;
import pe.com.nttdata.model.Historico;
import pe.com.nttdata.model.Product;
import pe.com.nttdata.model.ProductResponse;
import pe.com.nttdata.model.TypeCustomer;
import pe.com.nttdata.model.TypeProduct;
import pe.com.nttdata.request.ProductRequest;
import pe.com.nttdata.response.ConsultaSaldo;
import pe.com.nttdata.util.Constantes;
import reactor.core.publisher.Mono;

@Service

public class OperacionesService {
	private static final Logger LOG = LoggerFactory.getLogger(OperacionesService.class);
	@Autowired
	private CustomerClientInf customerClientInf;

	@Autowired
	private TypeCustomerClientInf typeCustomerClientInf;

	@Autowired
	private ProductClientInf productClient;
	
	@Autowired
	private HistoricoClientInf historicoClientInf;
	public Historico save(Historico historico) {
		return this.historicoClientInf.save(historico);
	}

	public Mono<CustomerResponse> consultarCliente(String id) {
		CustomerResponse response = new CustomerResponse();
		Customer customer = this.customerClientInf.findById(id);
		List<TypeCustomer> lst = this.typeCustomerClientInf.searchByIdCustomer(customer.getId());
		List<Product> products = this.productClient.findByIdCustomers(customer.getId());
		customer.setTypeCustomers(lst);
		customer.setProducts(products);
		response.setCustomer(customer);
		LOG.info("Response del cliente: {}", response);
		return Mono.just(response);
	}

	public Product findByIdProduct(String id) {
		return this.productClient.findById(id);
	}
	
	public ConsultaSaldo consultarSaldo(String id) {
		ObjectMapper mapper = new ObjectMapper();
		ConsultaSaldo response =new ConsultaSaldo();
		Product product= this.productClient.findById(id);
		Customer customer = this.customerClientInf.findById(product.getIdCustomer());
		
		List<TypeProduct> typeProducts=productClient.getAllTypeProduct();
		List<TypeProduct> listTypeProduct = mapper.convertValue(typeProducts, new TypeReference<List<TypeProduct>>() {});
		for (TypeProduct typeProduct : listTypeProduct) {
			if (typeProduct.getId().equals(product.getIdTypeProduct())) {
				response.setType(typeProduct.getType());
				response.setAccount(typeProduct.getAccount());
			}
		}
		response.setSaldoTotal(product.getAmount());
		response.setNombreUsuario(customer.getName().concat(" ").concat(customer.getSurname()));
		
		
		response.setFechaConsulta(LocalDateTime.now());
		LOG.info("Response del cliente: {}", response);
		return response;
	}

	public ProductResponse save(ProductRequest request) throws UnknownHostException {
		ProductResponse response=new ProductResponse();
		Product pr;
		ObjectMapper mapper = new ObjectMapper();
		
		List<TypeProduct> lstTypeProduct = this.productClient.getAllTypeProduct();
		LOG.info("Tamanio de lista TypeProduct: {}", lstTypeProduct.size());
		List<TypeProduct> typeProductList = mapper.convertValue(lstTypeProduct, new TypeReference<List<TypeProduct>>() {});
		for (TypeProduct typeProduct : typeProductList) {
			if (typeProduct.isStatus()) {
				pr = this.findByIdProduct(request.getId());
				LOG.info("Producto: {}",pr);
				if (pr!=null) {
					pr.setNumberOfMovements(pr.getNumberOfMovements()+1);
					pr.setAction(request.getAction());
					if (request.getAction().equalsIgnoreCase(Constantes.DEPOSITO)) {
						pr.setAmount(pr.getAmount()+request.getAmount());
						this.setHistoric(pr);
					} else if (request.getAction().equalsIgnoreCase(Constantes.RETIRO)) {
						if (pr.getAmount()>0 && pr.getAmount()>=request.getAmount()) {
							pr.setAmount(pr.getAmount() - request.getAmount());
							this.setHistoric(pr);
						}else {
							response.setCodRequest("-1");
							response.setMsgRequest("Salda insuficiente");
							response.setProduct(null);
							return response;
						}
					} else if (request.getAction().equalsIgnoreCase(Constantes.COMPRA)) {
						if (pr.getAmount()>0 && pr.getAmount() >= request.getAmount()) {
							BootCoin bootcoin = new BootCoin();
							numberBootcoin = (double) request.getAmount()/bootcoin.getSellingRate();

							Application solicitud = new Application();
							solicitud.setAmount(request.getAmount());
							solicitud.setPayMode(request.getPayMode());
							solicitud.setStatus(false);
							solicitud.setTransactionNumber(new Long());

							LOG.info("Solicitud nuevo ==========> {}", solicitud);
							return ApplicationInf.save(solicitud);
						}else {
							response.setCodRequest("-1");
							response.setMsgRequest("Salda insuficiente para compra de Bootcoin");
							response.setProduct(null);
							return response;
						}
					} else if (request.getAction().equalsIgnoreCase(Constantes.VENTA)) {
						pr.setAmount(pr.getAmount()+request.getAmount());
						this.setHistoric(pr);
					}
					Product products=this.productClient.save(pr);
					response.setCodRequest("0");
					response.setMsgRequest("ok");
					response.setProduct(products);
					return response;
				}else {
					response.setCodRequest("-2");
					response.setMsgRequest("Not data: ");
				}
				
				
			}
			
			
		}
		
		
		
	
		return response;

	}
	
	private Historico setHistoric(Product product) throws UnknownHostException{
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		String formattedDateTime = currentDateTime.format(formatter);
		Historico historico=new Historico();
		historico.setMontoActual(product.getAmount());
		historico.setIdOpreracion(product.getId());
		historico.setCommission(0);
		historico.setNumberOfMovements(product.getNumberOfMovements());
		historico.setNumberOfCredit(0);
		historico.setLimitCredit(0);
		historico.setAction(product.getAction());
		historico.setIdTypeProduct(product.getIdTypeProduct());
		historico.setIdCustomer(product.getIdCustomer());
		historico.setFechaOperacion(formattedDateTime);
		historico.setDevice(Inet4Address.getLocalHost().getHostName());
		LOG.info("Historicos==========> {}",historico);
		return historicoClientInf.save(historico);
		
	}

}
