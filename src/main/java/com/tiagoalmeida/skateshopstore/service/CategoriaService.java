package com.tiagoalmeida.skateshopstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagoalmeida.skateshopstore.domain.Categoria;
import com.tiagoalmeida.skateshopstore.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	public Categoria buscar( Integer id ) {
		
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElse(null);
		
	}

}
