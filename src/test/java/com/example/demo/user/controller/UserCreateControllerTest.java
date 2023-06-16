package com.example.demo.user.controller;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.demo.mock.TestContainer;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.UserCreate;

public class UserCreateControllerTest {

	@Test
	void 사용자는_회원_가입을_할_수있고_회원가입된_사용자는_PENDING_상태이다() throws Exception {
		// given
		final TestContainer testContainer = TestContainer.builder()
			.uuidHolder(() -> "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.build();
		final UserCreate userCreate = UserCreate.builder()
			.address("Seoul")
			.nickname("kok202")
			.email("test@gmail.com")
			.build();

		// when
		ResponseEntity<UserResponse> result = testContainer.userCreateController.createUser(userCreate);

		// then
		assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
		assertThat(result.getBody()).isNotNull();
		assertThat(result.getBody().getEmail()).isEqualTo("test@gmail.com");
		assertThat(result.getBody().getNickname()).isEqualTo("kok202");
		assertThat(result.getBody().getLastLoginAt()).isNull();
		assertThat(testContainer.userRepository.getById(1).getCertificationCode()).isEqualTo(
			"aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");

	}

}