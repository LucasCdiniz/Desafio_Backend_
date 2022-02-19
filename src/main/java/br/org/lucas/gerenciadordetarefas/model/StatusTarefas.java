package br.org.lucas.gerenciadordetarefas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_statusTarefas")
public class StatusTarefas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O atributo Prioridade não pode ser nulo ou vazio!")
	@Size(min = 5, max = 50, message = "O atributo título deve ter no mínimo 5 e no máximo 50 caracteres")
	private String status;
	
	@OneToMany(mappedBy = "statusTarefas", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("statusTarefas")
	private List<Tarefas> tarefas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Tarefas> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefas> tarefas) {
		this.tarefas = tarefas;
	}
	
	
	
}
