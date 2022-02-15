package br.com.thiago.psimanager.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.thiago.psimanager.dto.AtendimentoRequisicao;
import br.com.thiago.psimanager.model.Atendimento;
import br.com.thiago.psimanager.model.StatusAtendimento;
import br.com.thiago.psimanager.service.AtendimentoService;
import br.com.thiago.psimanager.service.UsuarioService;

@Controller
@RequestMapping("atendimentos")
public class AtendimentoController {
	
	@Autowired
	private AtendimentoService service;
	
	@Autowired
	private UsuarioService servUsuario;
	
	@Cacheable("antendimentos_por_usuario")
	@GetMapping
	public String pacientes(Model model, Principal principal) {

		List<Atendimento> atendimentos = this.service.pegarTodosPorUsuario(principal.getName(), 0);
		
		model.addAttribute("atendimentos", atendimentos);
		return "atendimento/atendimentos";
	}
	
	@GetMapping("/novo")
	public String novo(AtendimentoRequisicao req) {
		
		return "atendimento/novo";
	}
	
	@GetMapping("/editar")
	public String editar(AtendimentoRequisicao req) {
		
		return "atendimento/editar";
	}
	
	@CacheEvict(value = "antendimentos_por_usuario", allEntries = true)
	@PostMapping("/adicionar")
	public String adicionar(@Valid AtendimentoRequisicao req, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("foi..no..if....");
			return "atendimento/novo";
		}
		//Atendimento at = req.toAtendimento();
		//String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//at.setUsuario(servUsuario.pegarPorID(username));
		//service.inserir(at);
		System.out.println("NAO...foi..no..if....");
		return "redirect:/atendimentos";
	}
	
	@GetMapping("/{status}")
	public String st(@PathVariable String status, Model model) {
		
		List<Atendimento> atendimentos = service.pegarTodosPorStatusPaginado(StatusAtendimento.valueOf(status.toUpperCase()), 1);
		
		model.addAttribute("atendimentos", atendimentos);
		model.addAttribute("status", status);
		
		return "atendimento/atendimentos";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/atendimentos";
	}
}
