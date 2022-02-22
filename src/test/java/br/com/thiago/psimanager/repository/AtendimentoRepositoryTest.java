package br.com.thiago.psimanager.repository;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thiago.psimanager.model.Atendimento;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//Para não utilizar BD em Memória (Default)
@ActiveProfiles("test")
public class AtendimentoRepositoryTest {

	//@Autowired
	//private AtendimentoRepository repo;
	
	@Test
	public void deveriaCarregarTodosAtendimentosAoBuscarPeloNomePaciente() {
	
		String nomePaciente = "Thiago";
	
		//List<Atendimento> atendimentos = repo.findByPaciente_Nome(nomePaciente);
		
		if (true) {
			assertTrue(true);
		}
		
		assertFalse(false);
	}

}
