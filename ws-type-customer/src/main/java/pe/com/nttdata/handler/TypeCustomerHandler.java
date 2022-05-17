package pe.com.nttdata.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.nttdata.model.TypeCustomer;
import pe.com.nttdata.service.TypeCustomerServiceInf;
import reactor.core.publisher.Mono;


@Component
public class TypeCustomerHandler {
	private final TypeCustomerServiceInf typeCustomerService;
	
	static Mono<ServerResponse>notFound=ServerResponse.notFound().build();
	
	@Autowired
	public TypeCustomerHandler (TypeCustomerServiceInf typeCustomerService) {
		 this.typeCustomerService=typeCustomerService;
	}
	
	public Mono<ServerResponse> addTypeCustomer(ServerRequest request) {
		var typeProduct=request.bodyToMono(TypeCustomer.class);
		return typeProduct.flatMap(t->ServerResponse.status(HttpStatus.CREATED)
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(this.typeCustomerService.addTypeCustomer(t),TypeCustomer.class));
	}
	

	



}
