package br.com.thiago.psimanager.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.service.AtendimentoService;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoRest {

	@Autowired
	private AtendimentoService service;
	
	@GetMapping("/{pagina}")
	public List<Atendimento> getAtendimentosPaginado(@PathVariable Integer pagina) {

		List<Atendimento> atendimentos = this.service.pegarTodosPaginado(pagina);
		
		return atendimentos;
	}
}
