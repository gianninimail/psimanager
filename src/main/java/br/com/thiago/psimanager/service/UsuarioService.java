package br.com.thiago.psimanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Usuario;
import br.com.thiago.psimanager.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario pegarPorID(String username) {
		
		Optional<Usuario> usuario = repo.findById(username);
		
		return usuario.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = repo.findById(username);
		
		if (usuario.isPresent()) {
			
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos");
	}
}
