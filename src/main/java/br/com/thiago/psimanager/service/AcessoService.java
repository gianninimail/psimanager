package br.com.thiago.psimanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiago.psimanager.model.Acesso;
import br.com.thiago.psimanager.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository repo;
	
	public Acesso pegarPorID(Long id) {
		
		Acesso p = repo.findById(id).get();
		
		return p;
	}
	
	public List<Acesso> pegarTodos() {
		
		List<Acesso> lista = new ArrayList<Acesso>();
		
		repo.findAll().forEach(o -> lista.add(o));
		
		return lista;
	}
	
	public void inserir(Acesso obj) {
		
		repo.save(obj);
	}
	
	public void apagar(Long cpf) {
		
		repo.deleteById(cpf);
	}
	
	public void atualizar(Acesso obj) {
		
		repo.save(obj);
	}
}
