package br.com.thiago.psimanager.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.psimanager.model.Atendimento;

@Repository
public interface AtendimentoRepository extends PagingAndSortingRepository<Atendimento, Long>, JpaSpecificationExecutor<Atendimento> {
	
}
