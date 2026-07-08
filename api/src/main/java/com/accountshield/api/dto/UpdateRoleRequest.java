package com.accountshield.api.dto;

import com.accountshield.api.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request payload for updating user role")
public class UpdateRoleRequest {
    @Schema(description = "The new role for the user")
    private Role role;
}
