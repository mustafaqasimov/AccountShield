package com.accountshield.api.dto;

import com.accountshield.api.enums.Role;
import lombok.Data;

@Data
public class UpdateRoleRequest {
    private Role role;
}
