package com.example.demo.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.mock.FakeMailSender;

class CertificationServiceTest {

	@Test
	void 이메일과_컨텐츠가_제대로_만들어져서_보내지는지_테스트한다() throws Exception{
	    //given
		final FakeMailSender mailSender = new FakeMailSender();
		CertificationService certificationService = new CertificationService(mailSender);
	    //when
		certificationService.send("kok202@naver.com", 1, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
	    
	    //then
		assertThat(mailSender.email).isEqualTo("kok202@naver.com");
		assertThat(mailSender.title).isEqualTo("Please certify your email address");
		assertThat(mailSender.content).isEqualTo("Please click the following link to certify your email address: http://localhost:8080/api/users/1/verify?certificationCode=aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa" );

	 }
}