package br.com.thiago.psimanager.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.psimanager.model.Acesso;
import br.com.thiago.psimanager.service.AcessoService;

@RestController
@RequestMapping("/api/acessos")
public class AcessoRest {

	@Autowired
	private AcessoService service;
	
	@GetMapping
	public List<Acesso> getAtendimentosPaginado() {

		List<Acesso> acessos = this.service.pegarTodos();
		
		return acessos;
	}
}
