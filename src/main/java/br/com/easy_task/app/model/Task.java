package br.com.easy_task.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@NamedQuery(name = "Task.buscarTaks", query = "select t from Task t where t.dataDelecao = null order by t.status")
@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "text")
	private String descricao;
	private String titulo;
	
	@Column(name = "data_cadastro")
	private Calendar dataCadastro;
	
	@Column(name = "data_alteracao")
	private Calendar dataAlteracao;
	
	@Column(name = "data_delecao")
	private Calendar dataDelecao;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	
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

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Calendar getDataDelecao() {
		return dataDelecao;
	}

	public void setDataDelecao(Calendar dataDelecao) {
		this.dataDelecao = dataDelecao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@PrePersist
	@PreUpdate
	public void registrarDataCadastroAtualizacao() {
		this.dataAlteracao = Calendar.getInstance();
		
		if(this.dataCadastro == null) {
			this.dataCadastro = Calendar.getInstance();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", descricao=" + descricao + ", titulo="
				+ titulo + ", dataCadastro=" + dataCadastro
				+ ", dataAlteracao=" + dataAlteracao + ", dataDelecao="
				+ dataDelecao + ", status=" + status + "]";
	}
}
