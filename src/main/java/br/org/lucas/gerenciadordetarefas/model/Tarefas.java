package br.org.lucas.gerenciadordetarefas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tarefas")
public class Tarefas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Descrição não pode ser nulo ou vazio!")
	@Size(min = 5, max = 100, message = "O atributo título deve ter no mínimo 5 e no máximo 100 caracteres")
	private String descricao;
	
	@NotBlank(message = "O atributo Prioridade não pode ser nulo ou vazio!")
	@Size(min = 5, max = 25, message = "O atributo título deve ter no mínimo 5 e no máximo 25 caracteres")
	private String prioridade;
	
	@ManyToOne
	@JsonIgnoreProperties("tarefas")
	private StatusTarefas statusTarefas;
	
	@ManyToOne
	@JsonIgnoreProperties("tarefas")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public StatusTarefas getStatusTarefas() {
		return statusTarefas;
	}

	public void setStatusTarefas(StatusTarefas statusTarefas) {
		this.statusTarefas = statusTarefas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
