package com.accountshield.api.service;

import com.accountshield.api.dto.UserRequest;
import com.accountshield.api.dto.UserResponse;
import com.accountshield.api.entity.User;
import com.accountshield.api.mapper.UserMapper;
import com.accountshield.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("This email has already been registered!");
        }
        if (repository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("This username has already been taken!");
        }

        User user = mapper.toEntity(request);

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        User savedUser = repository.save(user);

        return mapper.toResponse(savedUser);
    }


}
