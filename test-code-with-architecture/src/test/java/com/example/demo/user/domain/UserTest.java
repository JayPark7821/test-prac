package com.example.demo.user.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;

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
		User user = User.from(userCreate, new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"));
	    //then
		assertThat(user.getId()).isNull();
		assertThat(user.getEmail()).isEqualTo("test@kakao.com");
		assertThat(user.getNickname()).isEqualTo("test");
		assertThat(user.getAddress()).isEqualTo("Pangyo");
		assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
		assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
	}
	@Test
	void UserUpdate_객체로_수정_수_있다() throws Exception{
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

		UserUpdate userUpdate = UserUpdate.builder()
			.nickname("testtest")
			.address("Pangyo")
			.build();
		//when
		final User updatedUser = user.update(userUpdate);
		//then
		assertThat(updatedUser.getId()).isEqualTo(1L);
		assertThat(updatedUser.getEmail()).isEqualTo("test@mail.com");
		assertThat(updatedUser.getNickname()).isEqualTo("testtest");
		assertThat(updatedUser.getAddress()).isEqualTo("Pangyo");
		assertThat(updatedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(updatedUser.getLastLoginAt()).isEqualTo(100L);
		assertThat(updatedUser.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
	}

	@Test
	void 로그인을_할_수_잇고_로그인시_마지막_로그인_시간이_변경된다() throws Exception{
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
		final User loginedUser = user.login(new TestClockHolder(100L));
		//then
		assertThat(loginedUser.getLastLoginAt()).isEqualTo(100L);
	}

	@Test
	void 인증_코드로_계정을_활성화_할_수_있다() throws Exception{
		//given
		final User user = User.builder()
			.id(1L)
			.email("test@mail.com")
			.nickname("test")
			.address("suwon")
			.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.status(UserStatus.PENDING)
			.lastLoginAt(100L)
			.build();

		//when
		final User certificatedUser = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
		//then
		assertThat(certificatedUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
	}

	@Test
	void 잘못된_인증_코드로_계정을_활성화_하려면_에러를_던진다() throws Exception{
		//given
		final User user = User.builder()
			.id(1L)
			.email("test@mail.com")
			.nickname("test")
			.address("suwon")
			.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.status(UserStatus.PENDING)
			.lastLoginAt(100L)
			.build();

		//when
		//then
		assertThatThrownBy(()->user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa22"))
			.isInstanceOf(CertificationCodeNotMatchedException.class);

	}
}