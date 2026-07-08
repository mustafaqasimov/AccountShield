package com.accountshield.api.controller;

import com.accountshield.api.dto.UpdateRoleRequest;
import com.accountshield.api.dto.UpdateStatusRequest;
import com.accountshield.api.entity.User;
import com.accountshield.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin operations")
public class AdminController {

    private final UserService userService;

    @Operation(summary = "Get All Users", description = "Retrieve a list of all users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Operation(summary = "Update User Status", description = "Update the status of a specific user")
    @PutMapping("/users/{id}/status")
    public ResponseEntity<Map<String, String>> updateUserStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request
    ) {
        userService.updateUserStatus(id, request.getStatus());
        return ResponseEntity.ok(Map.of("message", "User status updated successfully!"));
    }

    @Operation(summary = "Update User Role", description = "Update the role of a specific user")
    @PutMapping("/users/{id}/role")
    public ResponseEntity<Map<String, String>> updateUserRole(
            @PathVariable Long id,
            @RequestBody UpdateRoleRequest request
    ) {
        userService.updateUserRole(id, request.getRole());
        return ResponseEntity.ok(Map.of("message", "User role updated successfully!"));
    }
}
