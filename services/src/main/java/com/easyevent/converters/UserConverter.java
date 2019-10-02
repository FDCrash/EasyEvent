package com.easyevent.converters;

import com.easyevent.dto.base.RoleDto;
import com.easyevent.dto.base.UserDto;
import com.easyevent.entity.Role;
import com.easyevent.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto convert(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .registration(userEntity.isRegistration())
                .role(RoleDto.valueOf(userEntity.getRole().name()))
                .phone(userEntity.getPhone())
                .build();
    }

    public UserEntity convert(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .registration(userDto.isRegistration())
                .role(Role.valueOf(userDto.getRole().name()))
                .phone(userDto.getPhone())
                .build();
    }

}
