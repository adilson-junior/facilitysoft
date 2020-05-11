package br.com.facilitysoft.facilitysoft.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.facilitysoft.facilitysoft.dominio.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
