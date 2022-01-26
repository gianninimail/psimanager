package br.com.thiago.psimanager.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.thiago.psimanager.model.Pessoa;

public class PessoaSpecification {

	public static Specification<Pessoa> nome(String nome) {
		return (root, criteriaQuery, criteriaBuider) -> criteriaBuider.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Pessoa> cpf(Long cpf) {
		return (root, criteriaQuery, criteriaBuider) -> criteriaBuider.equal(root.get("cpf"), cpf);
	}
}
