package com.tiagoezc.geekhub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiagoezc.geekhub.model.Diretor;
import com.tiagoezc.geekhub.model.Genero;
import com.tiagoezc.geekhub.repository.GeneroRepository;

@Controller
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	GeneroRepository generoRepository;
	
	@GetMapping("/novo")
	public String adicionarGenero(Model model) {

		model.addAttribute("genero", new Genero());
		return "/auth/admin/admin-criar-genero";

	}
	
	@PostMapping("/salvar")
	public String salvarGenero(@Valid Genero genero, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "/auth/admin/admin-criar-genero";

		}
		
		generoRepository.save(genero);
		attributes.addFlashAttribute("mensagem", "Genero cadastrado com sucesso");
		return "redirect:/genero/novo";
		
	}
	
}
