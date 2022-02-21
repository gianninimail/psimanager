package br.com.thiago.psimanager.repository;


import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.thiago.psimanager.model.Atendimento;

@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//Para não utilizar BD em Memória (Default)
@ActiveProfiles("test")
public class AtendimentoRepositoryTest {

	@Autowired
	private AtendimentoRepository repo;
	
	@Test
	public void deveriaCarregarTodosAtendimentosAoBuscarPeloNomePaciente() {
	
		String nomePaciente = "Thiago";
	
		List<Atendimento> atendimentos = repo.findByPaciente_Nome(nomePaciente);
	}

}
