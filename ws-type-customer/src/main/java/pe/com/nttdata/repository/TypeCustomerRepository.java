package pe.com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.nttdata.model.TypeCustomer;
import reactor.core.publisher.Flux;

@Repository
public interface TypeCustomerRepository extends ReactiveMongoRepository<TypeCustomer, String>{
	Flux<TypeCustomer>findAllByIdCustomer(String idCustomer);
}
