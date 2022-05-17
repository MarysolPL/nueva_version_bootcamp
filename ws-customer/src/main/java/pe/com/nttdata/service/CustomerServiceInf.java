package pe.com.nttdata.service;

import pe.com.nttdata.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerServiceInf {
	public Mono<Customer>  save(Customer customer);
	public Flux<Customer>findAll();
	public Mono<Customer>  findById(String id);
	public Mono<Void> delete(Customer producto);
	
}
