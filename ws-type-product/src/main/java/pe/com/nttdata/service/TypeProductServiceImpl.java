package pe.com.nttdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.nttdata.model.TypeProduct;
import pe.com.nttdata.repository.TypeProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class TypeProductServiceImpl implements  TypeProductServiceInf {
	@Autowired
	private TypeProductRepository typeProductRepository;

	@Override
	public Mono<TypeProduct> save(Mono<TypeProduct> typeProduct) {
		
		return typeProduct.flatMap(this.typeProductRepository::save);
	}

	@Override
	public Flux<TypeProduct> findAll() {
		return this.typeProductRepository.findAll();
	}

	@Override
	public Mono<TypeProduct> findById(String id) {
		return this.typeProductRepository.findById(id);
	}

	@Override
	public Mono<Void> delete(TypeProduct producto) {
		return this.typeProductRepository.delete(producto);
	}

	



}
