package com.tiagoezc.geekhub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Informe o nome do gênero para ser cadastrado.")
	@Size(min = 3, message = "O nome do gênero deve ter no mínimo 3 caracteres.")
	@Column(unique = true)
	private String nome;
	
}
