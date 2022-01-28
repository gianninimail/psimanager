package br.com.thiago.psimanager.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.thiago.psimanager.model.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, String>, JpaSpecificationExecutor<Usuario> {

	Usuario findByUsername(String username);
}
