package com.tiagoezc.geekhub.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank(message = "O nome do filme é obrigatório.")
	@Size(max = 20, message = "O nome do filme deve ter no máximo 20 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 20)
	private String nome;
	
	@NotNull(message = "Informe a duração do filme.")
	@Column(name = "duracao", nullable = false)
	private Integer duracao; // em minutos
	
	@NotBlank(message = "Informe a sinopse do filme.")
	@Size(max = 200, message = "A sinopse do filme deve conter no máximo 200 caracteres.")
	@Column(name = "sinopse", nullable = false, length = 200)
	private String sinopse;
	
	// Falta adicionar relacionamento com diretor e genero
	// ver como faz pra adicionar imagem (capa), se eu preciso criar um atributo ou não
	
	@OneToMany(mappedBy = "filme")
	private Set<Genero> generos; 
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diretor_id")
	private Diretor diretor;
	
	private String nomeImagem;
	
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}

	public Set<Genero> getGeneros() {
	        return generos;
	    }

	    public void setGeneros(Set<Genero> generos) {
	        this.generos = generos;
	        for (Genero genero : generos) {
	            genero.setFilme(this);
	        }
	    }

	    public void addGenero(Genero genero) {
	        generos.add(genero);
	        genero.setFilme(this);
	    }

	    public void removeGenero(Genero genero) {
	        generos.remove(genero);
	        genero.setFilme(null);
	    }

		public String getNomeImagem() {
			return nomeImagem;
		}

		public void setNomeImagem(String nomeImagem) {
			this.nomeImagem = nomeImagem;
		}
	
}
