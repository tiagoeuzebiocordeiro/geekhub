package com.tiagoezc.geekhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagoezc.geekhub.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

	
	
}
