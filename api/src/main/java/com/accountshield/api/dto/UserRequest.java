package com.accountshield.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Request payload for creating a new user")
public class UserRequest {

    @Schema(description = "The unique username of the user", example = "mustafa_dev")
    @NotBlank(message = "Username is required")
    String username;

    @Schema(description = "The password for the user", example = "securePassword123")
    @NotNull(message = "Password is required")
    String password;

    @Schema(description = "The email address of the user", example = "mustafa.dev@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not valid")
    String email;
}
