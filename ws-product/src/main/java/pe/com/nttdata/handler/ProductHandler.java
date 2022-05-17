package pe.com.nttdata.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.model.Product;
import pe.com.nttdata.repository.ProductRepository;
import pe.com.nttdata.service.ProductServiceInf;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class ProductHandler {
	private final ProductRepository productService;
	
	static Mono<ServerResponse>notFound=ServerResponse.notFound().build();
	
	@Autowired
	public ProductHandler (ProductRepository productService) {
		 this.productService=productService;
	}
	
	public Mono<ServerResponse> addProduct(ServerRequest request) {
		var typeProduct=request.bodyToMono(Product.class);
		return typeProduct.flatMap(t->ServerResponse.status(HttpStatus.CREATED)
				.contentType(MediaType.TEXT_EVENT_STREAM)
				
				.body(this.productService.save(t),Product.class));
	}
	
	public Mono<ServerResponse> getAllProduct(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(this.productService.findAll()
				.log("Function: "),Product.class);
	}
	public Mono<ServerResponse> findByIdProduct(ServerRequest request) {
		var id=request.pathVariable("id");
		log.info("id: {}",id);
		return this.productService.findById(id)
				.flatMap(t->ServerResponse.ok()
						.contentType(MediaType.TEXT_EVENT_STREAM)
						.body(fromValue(t))
						.doOnNext(l->log.info("Details customer: {}",t))
						.switchIfEmpty(notFound));
	}
	
	public Mono<ServerResponse> deleteProduct(ServerRequest request) {
		String id = request.pathVariable("id");
		log.info("id: {}",id);
		Mono<Product> customer = productService.findById(id);
		log.info("Delete customer: {}",customer);
		return customer.flatMap(p-> productService.delete(p)
				.doOnNext(l->log.info("Delete customer: {}",l))
				.then(ServerResponse.noContent().build()))
				.switchIfEmpty(notFound);
		
	}
	


}
