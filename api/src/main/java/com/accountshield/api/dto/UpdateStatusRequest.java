package com.accountshield.api.dto;

import com.accountshield.api.enums.ActiveStatus;
import com.accountshield.api.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request payload for updating user status")
public class UpdateStatusRequest {
    @Schema(description = "The new status for the user")
    private ActiveStatus status;
}
