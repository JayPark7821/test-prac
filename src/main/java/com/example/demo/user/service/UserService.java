package com.example.demo.user.service;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.common.service.port.UuidHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserUpdate;
import com.example.demo.user.infrastructure.UserEntity;
import com.example.demo.user.service.port.UserRepository;

import java.time.Clock;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CertificationService certificationService;
    private final UuidHolder uuidHolder;
    private final ClockHolder clockHolder;

    public User getByEmail(String email) {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
            .orElseThrow(() -> new ResourceNotFoundException("Users", email));
    }

    public User getById(long id) {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
            .orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Transactional
    public User create(UserCreate userCreate) {
        final User user = User.from(userCreate, uuidHolder);
        final User savedUser = userRepository.save(user);
        certificationService.send(savedUser.getEmail(), savedUser.getId(), savedUser.getCertificationCode());
        return savedUser;
    }

    @Transactional
    public User update(long id, UserUpdate userUpdate) {
        final User user = getById(id);
        final User updatedUser = user.update(userUpdate);

        return userRepository.save(updatedUser);
    }

    @Transactional
    public void login(long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        final User loginedUser = user.login(clockHolder);
        userRepository.save(loginedUser);

    }

    @Transactional
    public void verifyEmail(long id, String certificationCode) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Users", id));
        final User certificatedUser = user.certificate(certificationCode);
        userRepository.save(certificatedUser);
    }

}