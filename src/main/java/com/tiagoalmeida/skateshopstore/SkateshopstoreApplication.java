package com.tiagoalmeida.skateshopstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiagoalmeida.skateshopstore.domain.Categoria;
import com.tiagoalmeida.skateshopstore.repository.CategoriaRepository;

@SpringBootApplication
public class SkateshopstoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SkateshopstoreApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
