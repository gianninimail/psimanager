package br.com.thiago.psimanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Pessoa;
import br.com.thiago.psimanager.repository.PessoaRepository;
import br.com.thiago.psimanager.specification.PessoaSpecification;
import br.com.thiago.psimanager.vo.PessoaProjecao;

@Service
public class PessoaService {

	private final PessoaRepository repo;

	public PessoaService(PessoaRepository _repo) {

		this.repo = _repo;
	}
	
	public Optional<Pessoa> pegarPorID(Long cpf) {
		
		return repo.findById(cpf);
	}
	
	public List<Pessoa> pegarTodos() {
		
		List<Pessoa> lista = new ArrayList<Pessoa>();
		
		repo.findAll().forEach(o -> lista.add(o));
		return lista;
	}
	
	public Page<Pessoa> pegarTodosPaginado(Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "cpf", "nome"));
		
		return repo.findAll(pag);
	}
	
	public List<Pessoa> pegarPorNome(String nome) {
		
		return repo.findByNome(nome);
	}
	
	public List<Pessoa> pegarPorNomeAndCPF(String nome, Long cpf) {
		
		return repo.findByNomeCPF(nome, cpf);
	}
	
	public List<PessoaProjecao> pegarCamposPorNome(String nome) {
		
		return repo.buscarCamposPorNome(nome);
	}
	
	
	public List<Pessoa> pegarComCriteriaPorNome(String nome) {
		
		return repo.findAll(PessoaSpecification.nome(nome));
	}
	
	public List<Pessoa> pegarComCriteria(Long cpf, String nome) {
		
		return repo.findAll(Specification.where(PessoaSpecification.cpf(cpf).or(PessoaSpecification.nome(nome))));
	}
	
	public void inserir(Pessoa obj) {
		
		repo.save(obj);
	}
	
	public void apagar(Long cpf) {
		
		repo.deleteById(cpf);
	}
	
	public void atualizar(Pessoa obj) {
		
		repo.save(obj);
	}
}
