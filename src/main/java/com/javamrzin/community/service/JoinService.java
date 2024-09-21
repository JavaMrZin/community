package com.javamrzin.community.service;

import com.javamrzin.community.dto.JoinDto;
import com.javamrzin.community.entity.Role;
import com.javamrzin.community.entity.User;
import com.javamrzin.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User joinUser(JoinDto joinDto) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        if (userRepository.existsByUsername(username)) {
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);

        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        return userRepository.save(user);
    }

}
