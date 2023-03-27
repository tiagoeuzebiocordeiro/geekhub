package com.tiagoezc.geekhub.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

/*Upload imagem = pronto
 * falta no HTML e conferir se está salvando*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiagoezc.geekhub.model.Diretor;
import com.tiagoezc.geekhub.model.Filme;
import com.tiagoezc.geekhub.model.Genero;
import com.tiagoezc.geekhub.repository.DiretorRepository;
import com.tiagoezc.geekhub.repository.FilmeRepository;
import com.tiagoezc.geekhub.repository.GeneroRepository;

@Controller
@RequestMapping("/filme")
public class FilmeController {

	public static String caminhoImagens = "C:\\Users\\TiagoEuzebio\\Documents\\imagens";

	@Autowired
	FilmeRepository filmeRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Autowired
	DiretorRepository diretorRepository;

	@GetMapping("/novo")
	public String adicionarFilme(Model model) {

		model.addAttribute("filme", new Filme());
		return "/auth/admin/admin-criar-filme";

	}

	@PostMapping("/salvar")
	public String salvarFilme(@Valid Filme filme, BindingResult result, RedirectAttributes attributes,
			@RequestParam("file") MultipartFile arquivo) {

		if (result.hasErrors()) {
			return "/auth/admin/admin-criar-filme";
		}

		filmeRepository.save(filme);
		
		/*Condição caso o caminho para salvar a imagem da capa do arquivo não exista*/
		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens+String.valueOf(filme.getId())+arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				filme.setNomeImagem(String.valueOf(filme.getId())+arquivo.getOriginalFilename()); // No banco só vai salvar o nome.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		attributes.addFlashAttribute("mensagem", "Filme cadastrado com sucesso");
		return "redirect:/auth/admin/admin-criar-filme";

	}
	
	/*Isso aqui serve pra aparecer os generos / diretores no combo box de selecionar*/
	
	@ModelAttribute("generos")
	public List<Genero> listaDeGeneros() {
		return generoRepository.findAll();
	}
	
	@ModelAttribute("diretor")
	public List<Diretor> listaDeDiretores() {
		return diretorRepository.findAll();
	}
	
}
