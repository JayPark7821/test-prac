package com.example.demo.user.controller;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.user.controller.port.UserReadService;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserUpdate;
import com.example.demo.user.infrastructure.UserEntity;

public class UserControllerTest {

	@Test
	void 사용자는_특정_유저의_정보를_개인정보는_소거된채_전달_받을_수_있다() throws Exception {
		final UserController userController = UserController.builder()
			.userReadService(new UserReadService() {
				@Override
				public User getByEmail(final String email) {
					return null;
				}

				@Override
				public User getById(final long id) {
					return User.builder()
						.id(1L)
						.email("kok202@naver.com")
						.nickname("kok202")
						.address("Seoul")
						.status(UserStatus.ACTIVE)
						.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
						.lastLoginAt(100L)
						.build();
				}
			})
			.build();
		// when
		ResponseEntity<UserResponse> result = userController.getUserById(1);

		// then
		assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
		assertThat(result.getBody()).isNotNull();
		assertThat(result.getBody().getEmail()).isEqualTo("kok202@naver.com");
		assertThat(result.getBody().getNickname()).isEqualTo("kok202");
		assertThat(result.getBody().getLastLoginAt()).isEqualTo(100);
		assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
	}

	@Test
	void 사용자는_존재하지_않는_유저의_아이디로_api_호출할_경우_404_응답을_받는다() throws Exception {
		final UserController userController = UserController.builder()
			.userReadService(new UserReadService() {
				@Override
				public User getByEmail(final String email) {
					return null;
				}

				@Override
				public User getById(final long id) {
					throw new ResourceNotFoundException("Users", id);
				}
			})
			.build();
		
		// when
		// then
		assertThatThrownBy(() -> userController.getUserById(1234567))
			.isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	void 사용자는_인증_코드로_계정을_활성화_시킬_수_있다() throws Exception {
		// given
		// when
		// then
		mockMvc.perform(
				get("/api/users/2/verify")
					.queryParam("certificationCode", "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"))
			.andExpect(status().isFound());
		UserEntity userEntity = userJpaRepository.findById(1L).get();
		assertThat(userEntity.getStatus()).isEqualTo(UserStatus.ACTIVE);
	}

	@Test
	void 사용자는_인증_코드가_일치하지_않을_경우_권한_없음_에러를_내려준다() throws Exception {
		// given
		// when
		// then
		mockMvc.perform(
				get("/api/users/2/verify")
					.queryParam("certificationCode", "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaac"))
			.andExpect(status().isForbidden());
	}

	@Test
	void 사용자는_내_정보를_불러올_때_개인정보인_주소도_갖고_올_수_있다() throws Exception {
		// given
		// when
		// then
		mockMvc.perform(
				get("/api/users/me")
					.header("EMAIL", "kok202@naver.com"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.email").value("kok202@naver.com"))
			.andExpect(jsonPath("$.nickname").value("kok202"))
			.andExpect(jsonPath("$.address").value("Seoul"))
			.andExpect(jsonPath("$.status").value("ACTIVE"));
	}

	@Test
	void 사용자는_내_정보를_수정할_수_있다() throws Exception {
		// given
		UserUpdate userUpdate = UserUpdate.builder()
			.nickname("kok202-n")
			.address("Pangyo")
			.build();

		// when
		// then
		mockMvc.perform(
				put("/api/users/me")
					.header("EMAIL", "kok202@naver.com")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(userUpdate)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.email").value("kok202@naver.com"))
			.andExpect(jsonPath("$.nickname").value("kok202-n"))
			.andExpect(jsonPath("$.address").value("Pangyo"))
			.andExpect(jsonPath("$.status").value("ACTIVE"));
	}

}