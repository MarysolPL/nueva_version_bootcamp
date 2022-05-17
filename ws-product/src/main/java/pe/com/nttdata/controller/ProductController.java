package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.model.Product;
import pe.com.nttdata.model.ProductRequest;
import pe.com.nttdata.service.ProductServiceInf;
import pe.com.nttdata.util.Constantes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Constantes.URL_PATH_REST)
@Log4j2
public class ProductController {
	@Autowired
	private ProductServiceInf productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> create(@RequestBody Product product) {
		 log.info("Reques: {}",product.toString());
		return this.productService.save(Mono.just(product));
	}

	@GetMapping
	public Flux<Product> getAll() {
		return productService.findAll();
	}
	@PostMapping("/getFindByIdAndIdTypeProduct")
	public Mono<Product> getFindByIdAndIdTypeProduct(@RequestBody ProductRequest request) {
		return productService.findByIdAndIdTypeProduct(request);
	}

	@GetMapping("/{id}")
	public Mono<Product> findByIds(@PathVariable String id) {
		return productService.findById(id);
	}

	@PutMapping("/{id}")
	public Mono<Product> update(@PathVariable String id, @RequestBody Product request) {
		return this.productService.findById(id).flatMap(p -> {

			p.setId(request.getId());
			p.setCommission(request.getCommission());
			p.setNumberOfMovements(request.getNumberOfMovements());
			p.setNumberOfCredit(request.getNumberOfCredit());
			p.setAmount(request.getAmount());
			p.setLimitCredit(request.getLimitCredit());
			p.setIdTypeProduct(request.getIdTypeProduct());
			p.setIdCustomer(request.getIdCustomer());
			return this.productService.save(Mono.just(p) );
		});
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return this.productService.findById(id).flatMap(c -> this.productService.delete(c));
	}

//	@GetMapping("/findByIdCustomer/{id}")
//	public Mono<Product> findByIdCustomer(@PathVariable String id) {
//		return this.productService.findByIdCustomer(id);
//	}
	
	@GetMapping("/findByIdCustomers/{id}")
	public Flux<Product> findByIdCustomers(@PathVariable String id) {
		return this.productService.findByIdCustomers(id);
	}
	
	@GetMapping("/eportFindByIdCustomer/{id}")
	public Flux<Product> eportFindByIdCustomer(@PathVariable String id) {
		return this.productService.findTop10ByOrderByIdCustomerDesc(id);
	}
	
	

}
