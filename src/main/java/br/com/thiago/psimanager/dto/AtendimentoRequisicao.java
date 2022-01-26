package br.com.thiago.psimanager.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.thiago.psimanager.model.Atendimento;

public class AtendimentoRequisicao {
	
	@NotBlank(message = "teste")//NotBlank.atendimentoRequisicao.fData=O campo deve ser preenchido!
	private String fData;
	@NotBlank
	private String fPaciente;
	private String fAnotacao1;
	private String fAnotacao2;

	public String getfData() {
		return fData;
	}

	public void setfData(String fData) {
		this.fData = fData;
	}

	public String getfPaciente() {
		return fPaciente;
	}

	public void setfPaciente(String fPaciente) {
		this.fPaciente = fPaciente;
	}

	public String getfAnotacao1() {
		return fAnotacao1;
	}

	public void setfAnotacao1(String fAnotacao1) {
		this.fAnotacao1 = fAnotacao1;
	}

	public String getfAnotacao2() {
		return fAnotacao2;
	}

	public void setfAnotacao2(String fAnotacao2) {
		this.fAnotacao2 = fAnotacao2;
	}

	public Atendimento toAtendimento() {
		
		Atendimento o = new Atendimento();
		
		o.setData(LocalDate.parse(fData));
		o.setAnotacoes(fAnotacao1 + '|' + fAnotacao2);
		//o.setPaciente(serPaciente.pegarPorID(10177062711L));
		
		return o;
	}
	
	
}
