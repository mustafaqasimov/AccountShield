package com.accountshield.api.controller;

import com.accountshield.api.dto.UpdateRoleRequest;
import com.accountshield.api.dto.UpdateStatusRequest;
import com.accountshield.api.entity.User;
import com.accountshield.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/users/{id}/status")
    public ResponseEntity<Map<String, String>> updateUserStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request
    ) {
        userService.updateUserStatus(id, request.getStatus());
        return ResponseEntity.ok(Map.of("message", "User status updated successfully!"));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<Map<String, String>> updateUserRole(
            @PathVariable Long id,
            @RequestBody UpdateRoleRequest request
    ) {
        userService.updateUserRole(id, request.getRole());
        return ResponseEntity.ok(Map.of("message", "User role updated successfully!"));
    }
}
