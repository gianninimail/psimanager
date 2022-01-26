package br.com.thiago.psimanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	@OneToMany
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	public Paciente() {}

	public Paciente(Long cpf, String nome, LocalDate dataNascimento, LocalDate dataPagamento) {
		super(cpf, nome, dataNascimento);
		this.dataPagamento = dataPagamento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
