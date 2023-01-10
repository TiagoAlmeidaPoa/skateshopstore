package com.tiagoalmeida.skateshopstore.service;

import org.springframework.mail.SimpleMailMessage;

import com.tiagoalmeida.skateshopstore.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
