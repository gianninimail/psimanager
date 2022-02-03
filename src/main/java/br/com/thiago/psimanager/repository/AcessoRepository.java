package br.com.thiago.psimanager.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.psimanager.interceptor.Acesso;

@Repository
public interface AcessoRepository extends PagingAndSortingRepository<Acesso, Long>, JpaSpecificationExecutor<Acesso> {

}
