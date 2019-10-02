package com.easyevent.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonIgnore
    private UUID id;

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private boolean registration;

    private RoleDto role;

    private String phone;

    @JsonIgnore
    public UserDto getUser(){
        return this;
    }
}
