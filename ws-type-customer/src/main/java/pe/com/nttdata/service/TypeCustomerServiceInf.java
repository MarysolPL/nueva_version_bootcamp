package pe.com.nttdata.service;

import pe.com.nttdata.model.TypeCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeCustomerServiceInf {
	 Mono<TypeCustomer>  addTypeCustomer(TypeCustomer typeCustomer);
	 Flux<TypeCustomer>gelAllTypeCustomer();
	
	 Flux<TypeCustomer>findAllByIdCustomer(String idCustomer);
	 
	 
}
