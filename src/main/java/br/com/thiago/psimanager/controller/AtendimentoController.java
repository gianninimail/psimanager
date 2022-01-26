package br.com.thiago.psimanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.thiago.psimanager.dto.AtendimentoRequisicao;
import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.service.AtendimentoService;

@Controller
@RequestMapping("atendimentos")
public class AtendimentoController {
	
	@Autowired
	private AtendimentoService service;
	
	@GetMapping("todos")
	public String pacientes(Model model) {

		List<Atendimento> atendimentos = this.service.pegarTodos();
		
		model.addAttribute("atendimentos", atendimentos);
		return "atendimento/atendimentos";
	}
	
	@GetMapping("novo")
	public String novo(AtendimentoRequisicao req) {
		
		return "atendimento/novo";
	}
	
	@PostMapping("adicionar")
	public String adicionar(@Valid AtendimentoRequisicao req, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("foi..no..if....");
			return "atendimento/novo";
		}
		Atendimento at = req.toAtendimento();
		//service.inserir(at);
		System.out.println("NAO...foi..no..if....");
		return "redirect:/atendimentos/todos";
	}
}
