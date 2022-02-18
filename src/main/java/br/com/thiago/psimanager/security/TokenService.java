package br.com.thiago.psimanager.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${psimanager.jwt.expiration}")
	private String expiration;
	
	@Value("${psimanager.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication auth) {
		
		Usuario logado = (Usuario) auth.getPrincipal();
		
		return Jwts
			.builder()
			.setIssuer("API do Psimanager")
			.setSubject(logado.getUsername().toString())
			.setIssuedAt(new Date())
			.setExpiration(new Date(new Date().getTime() + Long.valueOf(expiration)))
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}

	public boolean isTokenValido(String token) {
		
		try {
		
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	public String getUserID(String token) {
		
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		return body.getSubject();
	}

}
