package br.com.thiago.psimanager.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.service.PacienteService;

@Component
public class AtendimentoRequisicao {
	
	//@NotNull
	private static Long fId;
	@NotBlank//(message = "teste")//NotBlank.atendimentoRequisicao.fData=O campo deve ser preenchido!
	private static String fData;
	private static String fAnotacao1;
	private static String fAnotacao2;
	@NotNull
	private static String fPaciente;
	
	public AtendimentoRequisicao() {}
	
	public AtendimentoRequisicao(Atendimento atendimento) {
		
		fId = atendimento.getId();
		fData = atendimento.getData().toString();
		fPaciente = atendimento.getPaciente().getCpf().toString();
		fAnotacao1 = atendimento.getAnotacoes().split("\\|")[0];
		fAnotacao2 = atendimento.getAnotacoes().split("\\|")[1];
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long _fId) {
		fId = _fId;
	}

	public String getfData() {
		return fData;
	}

	public void setfData(String _fData) {
		fData = _fData;
	}

	public String getfAnotacao1() {
		return fAnotacao1;
	}

	public void setfAnotacao1(String _fAnotacao1) {
		fAnotacao1 = _fAnotacao1;
	}

	public String getfAnotacao2() {
		return fAnotacao2;
	}

	public void setfAnotacao2(String _fAnotacao2) {
		fAnotacao2 = _fAnotacao2;
	}

	public String getfPaciente() {
		return fPaciente;
	}

	public void setfPaciente(String _fPaciente) {
		fPaciente = _fPaciente;
	}

	public static Atendimento toAtendimento(PacienteService serv) {
		
		Atendimento o = new Atendimento();
		
		o.setId(fId);
		o.setData(LocalDate.parse(fData));
		o.setAnotacoes(fAnotacao1 + '|' + fAnotacao2);
		
		o.setPaciente(serv.pegarPorID(Long.valueOf(fPaciente)));
		
		return o;
	}
	
	public static List<AtendimentoRequisicao> converter(List<Atendimento> atendimentos) {
		
		return atendimentos.stream().map(AtendimentoRequisicao::new).collect(Collectors.toList());
	}
}
