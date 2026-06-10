package com.smartcourier.auth.mapper;

import com.smartcourier.auth.dto.response.UserDTO;
import com.smartcourier.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * MapStruct mapper for User entity ↔ UserDTO conversion.
 * Password is deliberately excluded from UserDTO.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);

    List<UserDTO> toDtoList(List<User> users);
}
