package com.accountshield.api.dto;

import com.accountshield.api.enums.ActiveStatus;
import com.accountshield.api.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private ActiveStatus status;
}
