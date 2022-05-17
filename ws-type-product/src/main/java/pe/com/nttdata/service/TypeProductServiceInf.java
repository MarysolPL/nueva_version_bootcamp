package pe.com.nttdata.service;

import pe.com.nttdata.model.TypeProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeProductServiceInf {
	public Mono<TypeProduct>  save(Mono<TypeProduct> typeProduct);
	public Flux<TypeProduct>findAll();
	public Mono<TypeProduct>  findById(String id);
	public Mono<Void> delete(TypeProduct producto);

}
