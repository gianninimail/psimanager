package br.com.thiago.psimanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Paciente;
import br.com.thiago.psimanager.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public Paciente pegarPorID(Long cpf) {
		
		Paciente p = repo.findById(cpf).get();
		
		return p;
	}
	
	public List<Paciente> pegarTodos() {
		
		List<Paciente> lista = new ArrayList<Paciente>();
		
		repo.findAll().forEach(o -> lista.add(o));
		
		return lista;
	}
	
	public Page<Paciente> pegarTodosPaginado(Integer pagina) {
		
		Pageable pag = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "cpf", "nome"));
		
		return repo.findAll(pag);
	}
	
	public List<Paciente> pegarPorNome(String nome) {
		
		return repo.findByNome(nome);
	}
	
	public List<Paciente> pegarPorNomeAndCPF(String nome, Long cpf) {
		
		return repo.findByNomeCPF(nome, cpf);
	}
	
	public void inserir(Paciente obj) {
		
		repo.save(obj);
	}
	
	public void apagar(Long cpf) {
		
		repo.deleteById(cpf);
	}
	
	public void atualizar(Paciente obj) {
		
		repo.save(obj);
	}
}
