package br.com.thiago.psimanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.model.StatusAtendimento;
import br.com.thiago.psimanager.service.AtendimentoService;

@Controller
public class HomeController {
	
	@Autowired
	private AtendimentoService service;

	@GetMapping("/home")
	public String home(Model model) {
		
		List<Atendimento> atendimentos = service.pegarTodosPorStatusPaginado(StatusAtendimento.NAO_REALIZADO, 0);
		
		model.addAttribute("atendimentos", atendimentos);
		
		return "home";
	}
	
	@GetMapping("/home2")
	public String home2(Model model) {
		
		List<Atendimento> atendimentos = service.pegarTodosPorStatusPaginado(StatusAtendimento.NAO_REALIZADO, 0);
		
		model.addAttribute("atendimentos", atendimentos);
		
		return "home2";
	}
}
