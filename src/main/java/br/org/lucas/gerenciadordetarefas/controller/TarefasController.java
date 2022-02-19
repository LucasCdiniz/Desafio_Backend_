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

import br.org.lucas.gerenciadordetarefas.model.Tarefas;
import br.org.lucas.gerenciadordetarefas.repository.TarefasRepository;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TarefasController {

	@Autowired
	private TarefasRepository tarefasRepository;
	
	//Lista todas as Tarefas cadastradas
	@GetMapping
	public ResponseEntity <List<Tarefas>> getAll(){
		return ResponseEntity.ok(tarefasRepository.findAll());
	}
	
	//Filtra as tarefas informando o id de uma determinada Tarefa
	@GetMapping("/{id}")
	public ResponseEntity <Tarefas> getById(@PathVariable Long id){
		return tarefasRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Filtra as tarefas pela descrição
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity <List<Tarefas>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(tarefasRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	//Filtra as tarefas pela descrição
	@GetMapping("/prioridade/{prioridade}")
	public ResponseEntity <List<Tarefas>> getByPrioridade(@PathVariable String prioridade){
		return ResponseEntity.ok(tarefasRepository.findAllByPrioridadeContainingIgnoreCase(prioridade));
	}
	
	//Inclusão de novas Tarefas
	@PostMapping
	public ResponseEntity <Tarefas> postTarefas(@Valid @RequestBody Tarefas tarefas){
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefasRepository.save(tarefas));
	}
	
	//Atualização de Tarefas
	@PutMapping
	public ResponseEntity <Tarefas> putTarefas(@Valid @RequestBody Tarefas tarefas){
		
		return tarefasRepository.findById(tarefas.getId())
				.map (resp -> ResponseEntity.status(HttpStatus.OK).body(tarefasRepository.save(tarefas)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	//Exclusão de Tarefas
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTarefas(@PathVariable long id) {
		return tarefasRepository.findById(id)
			.map(resposta -> {
				tarefasRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			})
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
