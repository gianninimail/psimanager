package br.com.thiago.psimanager.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${psimanager.jwt.expiration}")
	private String expiration;
	
	public String gerarToken(Authentication auth) {
		
		Usuario logado = (Usuario) auth.getPrincipal();
		
		return Jwts
			.builder()
			.setIssuer("API do Psimanager")
			.setSubject(logado.getUsername().toString())
			.setIssuedAt(new Date())
			.setExpiration(new Date(new Date().getTime() + Long.valueOf(expiration)))
			.signWith(SignatureAlgorithm.HS256, logado.getPassword())
			.compact();
	}

}
