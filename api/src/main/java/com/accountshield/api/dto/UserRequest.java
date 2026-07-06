package com.accountshield.api.dto;

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
public class UserRequest {

    @NotBlank(message = "Username is required")
    String username;

    @NotNull(message = "Password is required")
    String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not valid")
    String email;
}
