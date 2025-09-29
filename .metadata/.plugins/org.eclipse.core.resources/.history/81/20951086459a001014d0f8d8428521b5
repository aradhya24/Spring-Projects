package com.aurionpro.service;

import com.aurionpro.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendSimpleEmail(EmailRequest request) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(request.getTo());
		msg.setSubject(request.getSubject());
		msg.setText(request.getBody());
// From is optional — if you want to set explicitly:
// msg.setFrom("your.email@gmail.com");

		mailSender.send(msg);
	}
}
