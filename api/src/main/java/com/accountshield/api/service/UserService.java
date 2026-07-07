package com.accountshield.api.service;

import com.accountshield.api.dto.LoginRequest;
import com.accountshield.api.dto.LoginResponse;
import com.accountshield.api.dto.UserRequest;
import com.accountshield.api.dto.UserResponse;
import com.accountshield.api.entity.User;
import com.accountshield.api.enums.Role;
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
        user.setRole(Role.USER);

        User savedUser = repository.save(user);

        return mapper.toResponse(savedUser);
    }


    public LoginResponse loginUser(LoginRequest request) {
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .token("your-jwt-token")
                .build();
    }


}
