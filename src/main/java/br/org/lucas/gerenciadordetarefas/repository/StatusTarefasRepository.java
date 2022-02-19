package br.org.lucas.gerenciadordetarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.lucas.gerenciadordetarefas.model.StatusTarefas;

@Repository
public interface StatusTarefasRepository extends JpaRepository<StatusTarefas,Long>{
	
	public List <StatusTarefas> findAllByStatusContainingIgnoreCase (String status);

}
