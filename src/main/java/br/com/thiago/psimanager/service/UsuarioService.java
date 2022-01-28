package br.com.thiago.psimanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Usuario;
import br.com.thiago.psimanager.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario pegarPorID(String username) {
		
		Optional<Usuario> usuario = repo.findById(username);
		
		return usuario.get();
	}
}
