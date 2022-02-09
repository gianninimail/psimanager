package br.com.thiago.psimanager.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.psimanager.dto.AtendimentoRequisicao;
import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.service.AtendimentoService;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoRest {

	@Autowired
	private AtendimentoService service;
	
	@GetMapping("/pagina/{pagina}")
	public List<Atendimento> getAtendimentosPaginado(@PathVariable Integer pagina) {

		List<Atendimento> atendimentos = this.service.pegarTodosPaginado(pagina);
		
		return atendimentos;
	}
	
	@GetMapping("/por_id/{id}")
	public Atendimento por_id(@PathVariable Long id) {
		
		Optional<Atendimento> atendimento = this.service.pegarPorID(id);
		
		return atendimento.get();
	}
	
	@PutMapping("/editar")
	public Atendimento editar(@Valid @RequestBody AtendimentoRequisicao requisicao) {
		
		return this.service.atualizar(requisicao.toAtendimento());
	}
}
