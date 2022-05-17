package pe.com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.nttdata.model.TypeProduct;

@Repository
public interface TypeProductRepository extends ReactiveMongoRepository<TypeProduct, String>{
	
}
