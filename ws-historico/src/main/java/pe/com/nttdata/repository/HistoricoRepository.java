package pe.com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.nttdata.model.Historico;
import reactor.core.publisher.Flux;

@Repository
public interface HistoricoRepository extends ReactiveMongoRepository<Historico, String>{
	public Flux<Historico>findTop10ByOrderByIdDesc();
	
}
