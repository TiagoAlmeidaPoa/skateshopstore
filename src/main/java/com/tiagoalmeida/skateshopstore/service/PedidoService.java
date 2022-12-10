package com.tiagoalmeida.skateshopstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagoalmeida.skateshopstore.domain.Pedido;
import com.tiagoalmeida.skateshopstore.repository.PedidoRepository;
import com.tiagoalmeida.skateshopstore.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repository;
	
	public Pedido find( Integer id ) {
		
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:"+ id + ", tipo: " + Pedido.class.getName()
				));
		
	}

}
