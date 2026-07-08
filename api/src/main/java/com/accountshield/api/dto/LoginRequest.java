package com.accountshield.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "Login Request", description = "Request for user login")
public class LoginRequest {

    @Schema(description = "The unique username of the user", example = "mustafa_dev")
    @NotBlank(message = "Username is required")
    String username;

    @Schema(description = "The password for the user", example = "securePassword123")
    @NotNull(message = "Password is required")
    String password;
}
