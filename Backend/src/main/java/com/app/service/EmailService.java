package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shaileshkale244@gmail.com");
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code for delivery is: " + otp);
        mailSender.send(message);
    }

}
