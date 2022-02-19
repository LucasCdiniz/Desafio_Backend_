package br.org.lucas.gerenciadordetarefas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.lucas.gerenciadordetarefas.model.Tarefas;

@Repository
public interface TarefasRepository extends JpaRepository <Tarefas, Long>{

	public List <Tarefas> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	public List <Tarefas> findAllByPrioridadeContainingIgnoreCase(String prioridade);
}
