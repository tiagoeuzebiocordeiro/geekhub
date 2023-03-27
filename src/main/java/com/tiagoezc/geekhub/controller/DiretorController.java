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
import com.tiagoezc.geekhub.repository.DiretorRepository;

@Controller
@RequestMapping("/diretor")
public class DiretorController {

	@Autowired
	DiretorRepository diretorRepository;
	
	@GetMapping("/novo")
	public String adicionarDiretor(Model model) {

		model.addAttribute("diretor", new Diretor());
		return "/auth/admin/admin-criar-diretor";

	}
	
	@PostMapping("/salvar")
	public String salvarDiretor(@Valid Diretor diretor, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "/auth/admin/admin-criar-diretor";

		}
		
		diretorRepository.save(diretor);
		attributes.addFlashAttribute("mensagem", "Diretor cadastrado com sucesso");
		return "redirect:/diretor/novo";
		
	}
	
}
