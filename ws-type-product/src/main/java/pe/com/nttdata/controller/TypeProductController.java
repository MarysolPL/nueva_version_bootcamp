package pe.com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import pe.com.nttdata.model.TypeProduct;
import pe.com.nttdata.service.TypeProductServiceInf;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/type-products")
@Log4j2
public class TypeProductController {
	@Autowired
	private TypeProductServiceInf typeProductService;

	@PostMapping
	public Mono<TypeProduct> create(@RequestBody TypeProduct typeProduct) {
		log.info("Reques: {}",typeProduct.toString());
		return this.typeProductService.save(Mono.just(typeProduct));
	}

	@GetMapping
	public Flux<TypeProduct> getAll() {
		return typeProductService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<TypeProduct> findByIds(@PathVariable String id) {
		return typeProductService.findById(id);
	}

	@PutMapping("/{id}")
	public Mono<TypeProduct> update(@PathVariable String id, @RequestBody TypeProduct typeProduct) {
		return this.typeProductService.findById(id).flatMap(p -> {
			p.setId(typeProduct.getId());
			p.setType(typeProduct.getType());
			p.setAccount(typeProduct.getAccount());
			p.setStatus(typeProduct.isStatus());
			return this.typeProductService.save(Mono.just(p));
		});
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return this.typeProductService.findById(id)
				.flatMap(c -> this.typeProductService.delete(c));
	}
	
}
