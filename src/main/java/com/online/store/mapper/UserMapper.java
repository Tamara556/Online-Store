package com.online.store.mapper;

import com.online.store.dto.RegisterUserRequest;
import com.online.store.dto.UserDto;
import com.online.store.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);

}
