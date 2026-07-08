package com.accountshield.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for updating user email")
public class UpdateEmailRequest {

    @Schema(description = "The new email address for the user")
    @Email(message = "Invalid email format")
    String email;

}
