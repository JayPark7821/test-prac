package com.example.demo.user.controller.response;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

class UserResponseTest {

	@Test
	void User로_응답을_생성할_수_있다() throws Exception{
	    //given
		final User user = User.builder()
			.id(1L)
			.email("test@mail.com")
			.nickname("test")
			.address("suwon")
			.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.status(UserStatus.ACTIVE)
			.lastLoginAt(100L)
			.build();

		//when
		UserResponse userResponse = UserResponse.from(user);
		//then
		assertThat(userResponse.getId()).isEqualTo(1L);
		assertThat(userResponse.getEmail()).isEqualTo("test@mail.com");
		assertThat(userResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(userResponse.getLastLoginAt()).isEqualTo(100L);
	    
	}
}