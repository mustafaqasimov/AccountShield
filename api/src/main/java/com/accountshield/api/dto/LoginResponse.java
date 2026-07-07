package com.accountshield.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class LoginResponse {
    Long id;
    String username;
    String email;
    String token;
}
