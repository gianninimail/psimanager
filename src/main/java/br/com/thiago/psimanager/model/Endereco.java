package br.com.thiago.psimanager.model;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	private String logradouro;
	

	public Endereco() {}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
}
