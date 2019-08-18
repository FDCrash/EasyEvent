package com.easyevent.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private boolean registration;

    private RoleDto role;

    private String phone;
}
