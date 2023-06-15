package com.example.demo.user.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.controller.port.CertificationService;
import com.example.demo.user.service.port.MailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

	private final MailSender mailSender;

	@Override
	public void send(String email, long userId, String certificationCode) {
		final String certificationUrl = generateCertificationUrl(userId, certificationCode);
		final String title = "Please certify your email address";
		final String content = "Please click the following link to certify your email address: " + certificationUrl;
		mailSender.send(email, title, content);
	}


	private String generateCertificationUrl(long userId, String certificationCode) {
		return "http://localhost:8080/api/users/" + userId + "/verify?certificationCode=" + certificationCode;
	}
}
