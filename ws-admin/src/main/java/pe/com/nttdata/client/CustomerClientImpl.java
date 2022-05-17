package pe.com.nttdata.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.nttdata.model.Customer;

@Service
public class CustomerClientImpl implements CustomerClientInf{
	@Autowired
	private RestTemplate restTemplate; 
	@Override
	public Customer findById(String id) {
		return this.restTemplate.getForObject("http://localhost:8882/customers/"+id, Customer.class);
	}

}
