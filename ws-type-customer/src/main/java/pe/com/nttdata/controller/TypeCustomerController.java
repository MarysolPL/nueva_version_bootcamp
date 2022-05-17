package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.nttdata.model.TypeCustomer;
import pe.com.nttdata.service.TypeCustomerServiceInf;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/type-customers")
public class TypeCustomerController {
	@Autowired
	private TypeCustomerServiceInf serviceInf;
	
	@PostMapping
	public Mono<TypeCustomer> addTypeCustomer(@RequestBody TypeCustomer typeCustomer) {
		return this.serviceInf.addTypeCustomer(typeCustomer);
	}
	
	@GetMapping
	public Flux<TypeCustomer> getTypeCustomer() {
		return serviceInf.gelAllTypeCustomer();
	}

	
	
	
	@GetMapping("/buscar/{idCustomer}")
	public Flux<TypeCustomer> findAllByIdCustomer(@PathVariable String idCustomer) {
	
		return serviceInf.findAllByIdCustomer(idCustomer);
	}
}
