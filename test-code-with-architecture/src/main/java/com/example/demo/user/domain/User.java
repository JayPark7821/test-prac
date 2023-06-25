package com.example.demo.user.domain;

import java.time.Clock;
import java.util.UUID;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.common.service.port.UuidHolder;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

	private final Long id;
	private final String email;
	private final String nickname;
	private final String address;
	private final String certificationCode;
	private final UserStatus status;
	private final Long lastLoginAt;

	@Builder
	public User(final Long id, final String email, final String nickname, final String address,
		final String certificationCode, final UserStatus status,
		final Long lastLoginAt) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.address = address;
		this.certificationCode = certificationCode;
		this.status = status;
		this.lastLoginAt = lastLoginAt;
	}

	public static User from(UserCreate userCreate, UuidHolder uuidHolder) {
		return  User.builder()
			.email(userCreate.getEmail())
			.nickname(userCreate.getNickname())
			.address(userCreate.getAddress())
			.status(UserStatus.PENDING)
			.certificationCode(uuidHolder.random())
			.build();
	}

	public User update(UserUpdate userUpdate) {
		return User.builder()
			.id(this.id)
			.email(this.email)
			.nickname(userUpdate.getNickname())
			.address(userUpdate.getAddress())
			.status(this.status)
			.certificationCode(this.certificationCode)
			.lastLoginAt(this.lastLoginAt)
			.build();
	}

	public User login(ClockHolder clockHolder){
		return User.builder()
			.id(this.id)
			.email(this.email)
			.nickname(this.nickname)
			.address(this.address)
			.status(this.status)
			.certificationCode(this.certificationCode)
			.lastLoginAt(clockHolder.millis())
			.build();

	}

	public User certificate(String certificationCode){
		if (!this.certificationCode.equals(certificationCode)) {
			throw new CertificationCodeNotMatchedException();
		}
		return User.builder()
			.id(this.id)
			.email(this.email)
			.nickname(this.nickname)
			.address(this.address)
			.status(UserStatus.ACTIVE)
			.certificationCode(this.certificationCode)
			.lastLoginAt(this.lastLoginAt)
			.build();
	}
}
