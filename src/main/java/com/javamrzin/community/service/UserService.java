package com.javamrzin.community.service;

import com.javamrzin.community.model.Role;
import com.javamrzin.community.model.User;
import com.javamrzin.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        log.debug("password encoded = '{}'", password);
        user.setPassword(password);
        user.setEnabled(true);

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        return userRepository.save(user);
    }

}
