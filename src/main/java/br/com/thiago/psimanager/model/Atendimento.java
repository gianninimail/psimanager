package br.com.thiago.psimanager.model;

import java.io.Serializable;
import java.lang.Long;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "atendimentos")
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;   
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data;
	
	@Column(name = "esta_pago")
	private Boolean estaPago;
	
	private String anotacoes;
	
	@Enumerated(EnumType.STRING)
	private StatusAtendimento status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne
	private Paciente paciente;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private Pagamento pagamento;

	public Atendimento() {
		this.paciente = new Paciente();
	}   
	
	public Atendimento(Long id, LocalDate data, String anotacoes, Paciente paciente) {
		this.id = id;
		this.data = data;
		this.anotacoes = anotacoes;
		this.paciente = paciente;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getEstaPago() {
		return estaPago;
	}

	public void setEstaPago(Boolean estaPago) {
		this.estaPago = estaPago;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
