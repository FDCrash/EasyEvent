package com.easyevent.controllers;

import com.easyevent.dto.base.BaseLogin;
import com.easyevent.dto.base.RoleDto;
import com.easyevent.dto.base.UserDto;
import com.easyevent.services.implementation.AuthenticationService;
import com.easyevent.services.implementation.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Api
@RestController
@RequestMapping("/security")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @ApiOperation(value = "Register user", nickname = "AuthenticationController.registerUser")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User registration")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        Map id = Collections.singletonMap("id", userService.add(userDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get auth", nickname = "AuthenticationController.authenticate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Authenticate")})
    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody BaseLogin baseLogin) {
        RoleDto roleDto = authenticationService.getRoleByCredentials(baseLogin);
        if (roleDto != null) {
            return new ResponseEntity<>(Collections.singletonMap("role", roleDto.name()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
