package com.tiagoalmeida.skateshopstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tiagoalmeida.skateshopstore.domain.Categoria;
import com.tiagoalmeida.skateshopstore.repository.CategoriaRepository;
import com.tiagoalmeida.skateshopstore.service.exceptions.DataIntegrityException;
import com.tiagoalmeida.skateshopstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	public Categoria find( Integer id ) {
		
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+ id + ", tipo: " + Categoria.class.getName()
				));
		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		try {			
			find(id);
			repository.deleteById(id);
		}catch(DataIntegrityViolationException erro) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

}
