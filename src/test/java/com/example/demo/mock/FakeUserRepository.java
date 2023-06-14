package com.example.demo.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.service.port.UserRepository;

public class FakeUserRepository implements UserRepository {

	private final AtomicLong autoGeneratedId = new AtomicLong(0);
	private final List<User> data = Collections.synchronizedList(new ArrayList<>());

	@Override
	public Optional<User> findByIdAndStatus(final long id, final UserStatus userStatus) {
		return data.stream().filter(item -> item.getId().equals(id) && item.getStatus() == userStatus).findAny();
	}

	@Override
	public Optional<User> findByEmailAndStatus(final String email, final UserStatus userStatus) {
		return data.stream().filter(item -> item.getEmail().equals(email) && item.getStatus() == userStatus).findAny();
	}

	@Override
	public User save(final User user) {
		if (user.getId() == 0 || user.getId() == null) {
			User savedUser = User.builder()
				.id(autoGeneratedId.incrementAndGet())
				.email(user.getEmail())
				.nickname(user.getNickname())
				.address(user.getAddress())
				.certificationCode(user.getCertificationCode())
				.status(user.getStatus())
				.lastLoginAt(user.getLastLoginAt())
				.build();
			data.add(savedUser);
			return savedUser;
		} else {
			data.removeIf(item -> Objects.equals(item.getId(), user.getId()));
			data.add(user);
			return user;
		}
	}

	@Override
	public Optional<User> findById(final long id) {
		return data.stream().filter(item -> item.getId().equals(id)).findAny();
	}
}
