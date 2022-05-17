package pe.com.nttdata.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.nttdata.model.TypeCustomer;

@Service
public class TypeCustomerClientImpl implements TypeCustomerClientInf{
	@Autowired
	private RestTemplate restTemplate; 
	@Override
	public List<TypeCustomer> searchByIdCustomer(String idCustomer) {
		return this.restTemplate.getForObject("http://localhost:8882/type-customers/buscar/"+idCustomer, List.class);
	}

}
