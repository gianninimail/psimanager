package br.com.thiago.psimanager.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.thiago.psimanager.model.Usuario;
import br.com.thiago.psimanager.service.UsuarioService;

public class AuthFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UsuarioService usuarioService;
	
	public AuthFilter(TokenService tokenService, UsuarioService usuarioService) {
		
		this.tokenService = tokenService;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean tokenValido = tokenService.isTokenValido(token);
		if (tokenValido) {
			autenticarUser(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarUser(String token) {
		
		String user_id = tokenService.getUserID(token);
		Usuario usuario = usuarioService.pegarPorID(user_id);
		Authentication auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private String recuperarToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
		
			return null;
		}
		
		return token.substring(7, token.length());
	}

	
}
