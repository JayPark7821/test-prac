package com.example.demo.user.service;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.common.service.port.UuidHolder;
import com.example.demo.user.controller.port.CertificationService;
import com.example.demo.user.controller.port.UserService;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserUpdate;
import com.example.demo.user.service.port.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CertificationService certificationService;
    private final UuidHolder uuidHolder;
    private final ClockHolder clockHolder;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
            .orElseThrow(() -> new ResourceNotFoundException("Users", email));
    }

    @Override
    public User getById(long id) {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
            .orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Override
    @Transactional
    public User create(UserCreate userCreate) {
        final User user = User.from(userCreate, uuidHolder);
        final User savedUser = userRepository.save(user);
        certificationService.send(savedUser.getEmail(), savedUser.getId(), savedUser.getCertificationCode());
        return savedUser;
    }

    @Override
    @Transactional
    public User update(long id, UserUpdate userUpdate) {
        final User user = getById(id);
        final User updatedUser = user.update(userUpdate);

        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void login(long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        final User loginedUser = user.login(clockHolder);
        userRepository.save(loginedUser);

    }

    @Override
    @Transactional
    public void verifyEmail(long id, String certificationCode) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        final User certificatedUser = user.certificate(certificationCode);
        userRepository.save(certificatedUser);
    }

}