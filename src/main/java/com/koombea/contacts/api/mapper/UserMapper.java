package com.koombea.contacts.api.mapper;

import com.koombea.contacts.api.dto.UserRequest;
import com.koombea.contacts.api.dto.UserResponse;
import com.koombea.contacts.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequest request);

    UserResponse toResponse(User user);
}
