package br.com.thiago.psimanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.thiago.psimanager.model.Paciente;
import br.com.thiago.psimanager.service.PacienteService;

@Controller
public class PacienteController {
	
	@Autowired
	private PacienteService service;

	@GetMapping("/pacientes")
	public String pacientes(Model model) {

		List<Paciente> pacientes = this.service.pegarTodos();
		
		model.addAttribute("pacientes", pacientes);
		return "pacientes";
	}
}
