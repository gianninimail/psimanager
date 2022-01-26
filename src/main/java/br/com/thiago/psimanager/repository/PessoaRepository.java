package br.com.thiago.psimanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.psimanager.model.Pessoa;
import br.com.thiago.psimanager.vo.PessoaProjecao;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {
	
	List<Pessoa> findByNome(String _nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome = :_nome AND cpf = :_cpf")
	List<Pessoa> findByNomeCPF(String _nome, Long _cpf);
	
	@Query(value = "SELECT * FROM pessoas p WHERE p.nome LIKE %:_nome%", nativeQuery = true)
	List<Pessoa> buscarPorNome(String _nome);
	
	
	@Query(value = "SELECT nome FROM pessoas p WHERE p.nome LIKE %:_nome%", nativeQuery = true)
	List<PessoaProjecao> buscarCamposPorNome(String _nome);
}
