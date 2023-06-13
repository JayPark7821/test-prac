package com.example.demo.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.service.port.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<User> findByIdAndStatus(final long id, final UserStatus userStatus) {
		return userJpaRepository.findByIdAndStatus(id, userStatus).map(UserEntity::toModel);
	}

	@Override
	public Optional<User> findByEmailAndStatus(final String email, final UserStatus userStatus) {
		return userJpaRepository.findByEmailAndStatus(email, userStatus).map(UserEntity::toModel);
	}

	@Override
	public User save(final User user) {
		return userJpaRepository.save(UserEntity.fromModel(user)).toModel();
	}

	@Override
	public Optional<User> findById(final long id) {
		return userJpaRepository.findById(id).map(UserEntity::toModel);
	}
}
