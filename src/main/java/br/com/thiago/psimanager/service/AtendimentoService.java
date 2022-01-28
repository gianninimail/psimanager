package br.com.thiago.psimanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.model.StatusAtendimento;
import br.com.thiago.psimanager.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	private final AtendimentoRepository repo;

	public AtendimentoService(AtendimentoRepository _repo) {

		this.repo = _repo;
	}
	
	public Optional<Atendimento> pegarPorID(Long cpf) {
		
		return repo.findById(cpf);
	}
	
	public List<Atendimento> pegarTodos() {
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll().forEach(o -> lista.add(o));
		
		return lista;
	}
	
	public Page<Atendimento> pegarTodosPaginado(Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "data"));
		
		return repo.findAll(pag);
	}
	
	public void inserir(Atendimento obj) {
		
		repo.save(obj);
	}
	
	public void apagar(Long cpf) {
		
		repo.deleteById(cpf);
	}
	
	public void atualizar(Atendimento obj) {
		
		repo.save(obj);
	}

	public List<Atendimento> pegarTodosPorStatusPaginado(StatusAtendimento statusAtendimento, Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "data"));
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll(pag).forEach(o -> lista.add(o));
		
		return lista;
	}
	
	public List<Atendimento> pegarTodosPorUsuario(String username, Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "data"));
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll(pag).forEach(o -> lista.add(o));
		
		return lista;
	}
}
