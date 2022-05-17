package pe.com.nttdata.service;

import pe.com.nttdata.model.Historico;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HistoricoServiceInf {
	public Mono<Historico>  save(Historico historico);
	public Flux<Historico>findAll();
	public Mono<Historico>  findById(String id);
	public Mono<Void> delete(Historico producto);
	public Flux<Historico>findTop10ByOrderByDesc();
	
}
