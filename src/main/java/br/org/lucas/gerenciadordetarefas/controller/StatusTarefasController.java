package br.org.lucas.gerenciadordetarefas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.lucas.gerenciadordetarefas.model.StatusTarefas;
import br.org.lucas.gerenciadordetarefas.repository.StatusTarefasRepository;

@RestController
@RequestMapping("/statusTarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatusTarefasController {

	@Autowired
	private StatusTarefasRepository stTarefasRepository;
	
	@GetMapping
	public ResponseEntity<List<StatusTarefas>> getAll() {
		return ResponseEntity.ok(stTarefasRepository.findAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StatusTarefas> getById(@PathVariable long id){
		return stTarefasRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<StatusTarefas>> getByStatus(@PathVariable String status){
		return ResponseEntity.ok(stTarefasRepository.findAllByStatusContainingIgnoreCase(status));
	}
	
	@PostMapping
	public ResponseEntity<StatusTarefas> postStatus(@Valid @RequestBody StatusTarefas statusTarefas) {
		return ResponseEntity.status(HttpStatus.CREATED).body(stTarefasRepository.save(statusTarefas));
	}
	
	
	@PutMapping
	public ResponseEntity<StatusTarefas> putStatus(@Valid @RequestBody StatusTarefas statusTarefas) {
		return stTarefasRepository.findById(statusTarefas.getId())
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(stTarefasRepository.save(statusTarefas)))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStatus(@PathVariable long id) {
		return stTarefasRepository.findById(id)
			.map(resposta -> {
				stTarefasRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			})
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
