package com.example.springbootdeveloper.service;

import com.example.springbootdeveloper.domain.User;
import com.example.springbootdeveloper.dto.AddUserRequest;
import com.example.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
            return userRepository.save(User.builder()
            .email(dto.getEmail())
            // 패스워드 암호화
            .password(bCryptPasswordEncoder.encode(dto.getPassword()))
            .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

}
