package com.accountshield.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Schema(name = "Login Response", description = "Response for login operation")
public class LoginResponse {
    @Schema(description = "The unique ID of the user")
    Long id;
    @Schema(description = "The username of the user")
    String username;
    @Schema(description = "The email address of the user")
    String email;
    @Schema(description = "The access token for the user")
    String accessToken;
    @Schema(description = "The refresh token for the user")
    String refreshToken;
}
