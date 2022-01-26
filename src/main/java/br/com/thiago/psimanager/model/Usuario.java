package br.com.thiago.psimanager.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario extends Pessoa {

	private static final long serialVersionUID = 1L;
	private String login;
	private String pass;
	
	public Usuario() {}
	
	public Usuario(Long cpf, String nome, LocalDate dataNascimento, LocalDate dataPagamento, String login, String pass) {
		super(cpf, nome, dataNascimento);
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
