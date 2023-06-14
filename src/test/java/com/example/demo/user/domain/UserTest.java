package com.example.demo.user.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void UserCreate_객체로_생성할_수_있다() throws Exception{
	    //given
		UserCreate userCreate = UserCreate.builder()
			.email("test@kakao.com")
			.nickname("test")
			.address("Pangyo")
			.build();
	    //when
		User user = User.from(userCreate);
	    //then
		assertThat(user.getId()).isEqualTo(0L);
		assertThat(user.getEmail()).isEqualTo("test@kakao.com");
		assertThat(user.getNickname()).isEqualTo("test");
		assertThat(user.getAddress()).isEqualTo("Pangyo");
		assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
		assertThat(user.getCertificationCode()).isEqualTo();
	}
	@Test
	void UserUpdate_객체로_수정_수_있다() throws Exception{
	    //given
	    //when
	    //then
	}

	@Test
	void 로그인을_할_수_잇고_로그인시_마지막_로그인_시간이_변경된다() throws Exception{
	    //given
	    //when
	    //then
	}

	@Test
	void 인증_코드로_계정을_활성화_할_수_있다() throws Exception{
	    //given
	    //when
	    //then
	}

	@Test
	void 잘못된_인증_코드로_계정을_활성화_하려면_에러를_던진다() throws Exception{
	    //given
	    //when
	    //then
	}
}