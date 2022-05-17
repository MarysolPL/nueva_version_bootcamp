package pe.com.nttdata.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.model.Customer;
import pe.com.nttdata.service.CustomerServiceInf;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class CustomerHandler {

	

	private final CustomerServiceInf customerService;
	
	
	static Mono<ServerResponse>notFound=ServerResponse.notFound().build();
	
	@Autowired
	public CustomerHandler (CustomerServiceInf customerService) {
		
		 this.customerService=customerService;
	}
	
	public Mono<ServerResponse> addCustomer(ServerRequest request) {
		var typeCustomer=request.bodyToMono(Customer.class);
		return typeCustomer.flatMap(c->ServerResponse.status(HttpStatus.CREATED)
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(this.customerService.save(c),Customer.class));
	}
	
	public Mono<ServerResponse> getAllCustomer(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(this.customerService.findAll()
				.log("Function: "),Customer.class);
	}
	public Mono<ServerResponse> findByIdCustomer(ServerRequest request) {
		var id=request.pathVariable("id");
		log.info("id: {}",id);
		return this.customerService.findById(id)
				.flatMap(c->ServerResponse.ok()
						.contentType(MediaType.TEXT_EVENT_STREAM)
						.body(fromValue(c))
						.doOnNext(l->log.info("Details customer: {}",c))
						.switchIfEmpty(notFound));
	}
	
	public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
		String id = request.pathVariable("id");
		log.info("id: {}",id);
		Mono<Customer> customer = customerService.findById(id);
		log.info("Delete customer: {}",customer);
		return customer.flatMap(p-> customerService.delete(p)
				.doOnNext(l->log.info("Delete customer: {}",p))
				.then(ServerResponse.noContent().build()))
				.switchIfEmpty(notFound);
		
	}
	


}
