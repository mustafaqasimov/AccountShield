package com.accountshield.api.controller;

import com.accountshield.api.dto.UpdateEmailRequest;
import com.accountshield.api.entity.User;
import com.accountshield.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(Map.of(
                "status", "Success",
                "username", user.getUsername(),
                "email", user.getEmail() != null ? user.getEmail() : "Email not provided",
                "ActiveStatus", user.getActiveStatus(),
                "authorities", user.getAuthorities()
        ));
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateProfile(@AuthenticationPrincipal User user,
                                                           @RequestBody UpdateEmailRequest updateEmailRequest) {
        userService.updateProfile(user.getUsername(), updateEmailRequest.getEmail());
        return ResponseEntity.ok(Map.of("status", "Success",
                "message", "Email updated successfully"));
    }
}
