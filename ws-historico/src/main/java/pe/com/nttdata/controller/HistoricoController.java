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
import pe.com.nttdata.model.Historico;
import pe.com.nttdata.service.HistoricoServiceInf;
import pe.com.nttdata.util.Constantes;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Constantes.URL_PATH)
@Log4j2
public class HistoricoController {
	@Autowired
	private HistoricoServiceInf historicoService;

	@PostMapping
	public Mono<Historico> create(@RequestBody Historico historico) {
		log.info("Reques: {}", historico);
		return this.historicoService.save(historico);
	}
	
	@GetMapping
	public Flux<Historico> getAll() {
		return historicoService.findAll();
	}
	@GetMapping("/reporte")
	public Flux<Historico> findTop10ByOrderByIdDesc(){
		return this.historicoService.findTop10ByOrderByDesc();
	}
	@GetMapping("/{id}")
	public Mono<Historico> findByIds(@PathVariable String id) {
		return historicoService.findById(id);
	}

	@PutMapping("/{id}")
	public Mono<Historico> update(@PathVariable String id, @RequestBody Historico request) {
		return this.historicoService.findById(id).flatMap(p -> {
			return this.historicoService.save(p);
		});
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return this.historicoService.findById(id).flatMap(c -> this.historicoService.delete(c));
	}

	

}
