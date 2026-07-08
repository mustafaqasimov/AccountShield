package com.accountshield.api.controller;

import com.accountshield.api.dto.UpdateEmailRequest;
import com.accountshield.api.entity.User;
import com.accountshield.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Tag(name = "Profile", description = "User profile operations")
public class ProfileController {

    private final UserService userService;

    @Operation(summary = "Get User Profile",
            description = "Retrieve the profile information of the authenticated user")
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

    @Operation(summary = "Update User Profile",
            description = "Update the profile information of the authenticated user")
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateProfile(@AuthenticationPrincipal User user,
                                                           @RequestBody UpdateEmailRequest updateEmailRequest) {
        userService.updateProfile(user.getUsername(), updateEmailRequest.getEmail());
        return ResponseEntity.ok(Map.of("status", "Success",
                "message", "Email updated successfully"));
    }
}
