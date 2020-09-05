package com.blog.app.service.mappers;

import com.blog.app.repository.model.User;
import com.blog.app.service.request.SignUpUserRequest;
import lombok.NonNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapFromSignUpUserRequestToUser(@NonNull SignUpUserRequest signUpUserRequest);
}
