package com.accountshield.api.controller;

import com.accountshield.api.dto.LoginRequest;
import com.accountshield.api.dto.LoginResponse;
import com.accountshield.api.dto.UserRequest;
import com.accountshield.api.dto.UserResponse;
import com.accountshield.api.service.TokenService;
import com.accountshield.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication operations")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @Operation(summary = "User Registration", description = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @Operation(summary = "User Login", description = "Login to the application")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @Operation(summary = "Refresh Access Token",
            description = "Refresh the access token using the refresh token")
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        return ResponseEntity.ok(tokenService.refreshAccessToken(refreshToken));
    }

    @Operation(summary = "Verify Email", description = "Verify the user's email address")
    @GetMapping("/verify-email")
    public ResponseEntity<Map<String, String>> verifyEmail(@RequestParam("token") String token) {
        String result = tokenService.verifyEmail(token);
        return ResponseEntity.ok(Map.of("status", "Success", "message", result));
    }
}
