package br.com.thiago.psimanager.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.thiago.psimanager.model.Pagamento;

public class PagamentoRequisicao {
	
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotNull
	private Long fId;
	
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String fData;
	
	public Pagamento toAtendimento() {
		
		Pagamento o = new Pagamento();
		
		return o;
	}
}
