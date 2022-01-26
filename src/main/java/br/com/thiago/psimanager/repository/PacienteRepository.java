package br.com.thiago.psimanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.psimanager.model.Paciente;

@Repository
public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {
	
	List<Paciente> findByNome(String _nome);
	
	@Query("SELECT p FROM Paciente p WHERE p.nome = :_nome AND cpf = :_cpf")
	List<Paciente> findByNomeCPF(String _nome, Long _cpf);
	
	@Query(value = "SELECT * FROM pessoas p WHERE p.nome LIKE %:_nome%", nativeQuery = true)
	List<Paciente> buscarPorNome(String _nome);
}
