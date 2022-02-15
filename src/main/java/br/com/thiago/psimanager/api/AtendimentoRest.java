package br.com.thiago.psimanager.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.thiago.psimanager.dto.AtendimentoRequisicao;
import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.service.AtendimentoService;
import br.com.thiago.psimanager.service.PacienteService;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoRest {

	@Autowired
	private AtendimentoService service;
	
	@Autowired
	private PacienteService servPaciente;
	
	
	@GetMapping
	public List<Atendimento> getAtendimentos() {
		
		List<Atendimento> atendimentos = this.service.pegarTodos();
		
		return atendimentos;
	}
	
	@GetMapping("/paginado")
	public List<Atendimento> getAtendimentosPaginado(@RequestParam(required = true) Integer pagina, @RequestParam Integer qtd) {
		
		List<Atendimento> atendimentos = this.service.pegarTodosPaginado(pagina, qtd);
		
		return atendimentos;
	}
	
	@GetMapping("/paginacao")//Com o Enable @EnableSpringDataWebSupport ativado
	public List<Atendimento> getAtendimentosPaginado(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable paginacao) {
		
		List<Atendimento> atendimentos = this.service.pegarTodosPaginado(paginacao);
		
		return atendimentos;
	}
	
	@GetMapping("/pagina/{pagina}")
	public List<Atendimento> getAtendimentosPaginado(@PathVariable Integer pagina) {

		List<Atendimento> atendimentos = this.service.pegarTodosPaginado(pagina, 10);
		
		return atendimentos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atendimento> por_id(@PathVariable Long id) {
		
		Optional<Atendimento> atendimento = this.service.pegarPorID(id);
		
		if (atendimento.isPresent()) {
			return ResponseEntity.ok(atendimento.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CacheEvict(value = "antendimentos_por_usuario", allEntries = true)
	@PostMapping
	@Transactional
	public ResponseEntity<Atendimento> inserir(@RequestBody AtendimentoRequisicao requisicao, UriComponentsBuilder uriBuilder) {
		
		Atendimento at = AtendimentoRequisicao.toAtendimento(servPaciente);
		URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(at.getId()).toUri();
		
		return ResponseEntity.created(uri).body(at);
	}
	
	@CacheEvict(value = "antendimentos_por_usuario", allEntries = true)
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Atendimento> editar(@Valid @RequestBody AtendimentoRequisicao requisicao) {
		
		//Optional<Atendimento> atendimento = this.service.pegarPorID(id);
		
		//if (atendimento.isPresent()) {
		//	this.service.atualizar(requisicao.toAtendimento(servPaciente));
		//	return ResponseEntity.ok(requisicao.toAtendimento(servPaciente));
		//}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@CacheEvict(value = "antendimentos_por_usuario", allEntries = true)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Optional<Atendimento> atendimento = this.service.pegarPorID(id);
		
		if (atendimento.isPresent()) {
			this.service.apagar(id);
			return ResponseEntity.ok(atendimento.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
