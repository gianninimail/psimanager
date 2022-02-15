package br.com.thiago.psimanager.dto;

public class TokenDTO {

	private String token;
	private String string;

	public TokenDTO(String token, String string) {
		this.token = token;
		this.string = string;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
