package com.accountshield.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Response payload for user information")
public class UserResponse {
    @Schema(description = "The unique identifier for the user")
    Long id;
    @Schema(description = "The username for the user")
    String username;
    @Schema(description = "The email address for the user")
    String email;
    @Schema(description = "The date and time when the user was created")
    LocalDateTime createdAt;
}
