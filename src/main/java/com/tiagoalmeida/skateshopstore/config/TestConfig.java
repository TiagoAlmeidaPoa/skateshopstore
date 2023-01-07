package com.tiagoalmeida.skateshopstore.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tiagoalmeida.skateshopstore.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		dbService.instanciateTestDatabase();
		return true;
	}

}
