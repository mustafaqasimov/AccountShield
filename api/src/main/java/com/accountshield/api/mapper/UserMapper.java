package com.accountshield.api.mapper;

import com.accountshield.api.dto.UserRequest;
import com.accountshield.api.dto.UserResponse;
import com.accountshield.api.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User entity);
}

