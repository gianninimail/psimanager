package br.com.thiago.psimanager.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.model.StatusAtendimento;

@Repository
public interface AtendimentoRepository extends PagingAndSortingRepository<Atendimento, Long>, JpaSpecificationExecutor<Atendimento> {

	@Query("SELECT a FROM Atendimento a join a.usuario u WHERE u.username = :username")
	List<Atendimento> findByUsuario(@Param(value = "username")String username);
	
	@Cacheable("antendimentos_por_status")
	List<Atendimento> findByStatus(StatusAtendimento status, Pageable page);
	
	List<Atendimento> findByPaciente_Nome(String nomePaciente);//Acessar atributo do relacionamento com Paciente
}
