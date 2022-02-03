package br.com.thiago.psimanager.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.model.Paciente;

public class AtendimentoRequisicao {
	
	@NotNull
	private Long fId;
	@NotBlank//(message = "teste")//NotBlank.atendimentoRequisicao.fData=O campo deve ser preenchido!
	private String fData;
	private Paciente fPaciente;
	private String fAnotacao1;
	private String fAnotacao2;

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public String getfData() {
		return fData;
	}

	public void setfData(String fData) {
		this.fData = fData;
	}

	public Paciente getfPaciente() {
		return fPaciente;
	}

	public void setfPaciente(Paciente fPaciente) {
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
