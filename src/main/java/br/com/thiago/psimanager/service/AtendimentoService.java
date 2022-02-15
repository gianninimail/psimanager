package br.com.thiago.psimanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.model.StatusAtendimento;
import br.com.thiago.psimanager.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository repo;

	public Optional<Atendimento> pegarPorID(Long id) {
		
		return repo.findById(id);
	}
	
	public List<Atendimento> pegarTodos() {
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll().forEach(o -> lista.add(o));
		
		System.out.println(lista.size());
		
		return lista;
	}
	
	public List<Atendimento> pegarTodosPaginado(Integer pagina, Integer qtd) {
		
		Pageable pag = PageRequest.of(pagina, qtd, Sort.by(Sort.Direction.ASC, "data"));
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll(pag).forEach(o -> lista.add(o));;
		
		return lista;
	}
	
	public List<Atendimento> pegarTodosPaginado(Pageable paginacao) {
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll(paginacao).forEach(o -> lista.add(o));;
		
		return lista;
	}
	
	public void inserir(Atendimento obj) {
		
		repo.save(obj);
	}
	
	public void apagar(Long id) {
		
		repo.deleteById(id);
	}
	
	public Atendimento atualizar(Atendimento obj) {

		return repo.save(obj);
	}

	public List<Atendimento> pegarTodosPorStatusPaginado(StatusAtendimento statusAtendimento, Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "data"));
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findByStatus(statusAtendimento, pag).forEach(o -> lista.add(o));
		
		return lista;
	}
	
	public List<Atendimento> pegarTodosPorUsuario(String username, Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "data"));
		
		List<Atendimento> lista = new ArrayList<Atendimento>();
		
		repo.findAll(pag).forEach(o -> lista.add(o));
		
		return lista;
	}
}
