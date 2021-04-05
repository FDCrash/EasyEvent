package com.easyevent.controllers;

import com.easyevent.details.jwt.JwtTokenProvider;
import com.easyevent.dto.base.BaseLogin;
import com.easyevent.dto.base.UserDto;
import com.easyevent.dto.base.UserRegisterDto;
import com.easyevent.services.implementation.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Api
@RestController
@RequestMapping("/security")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @ApiOperation(value = "Register user", nickname = "AuthenticationController.registerUser")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User registration")})
    @PostMapping(path = "",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterDto userDto) {
        Map id = Collections.singletonMap("id", userService.register(userDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get auth", nickname = "AuthenticationController.authenticate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Authenticate")})
    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Object, Object>> authenticate(@RequestBody BaseLogin baseLogin) {
        try {
            String login = baseLogin.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, baseLogin.getPassword()));
            UserDto user = userService.getByLogin(login);

            if (user == null) {
                throw new UsernameNotFoundException("User with login: " + login + " not found");
            }

            String token = jwtTokenProvider.createToken(login, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("role", user.getRole());
            response.put("token", token);
            response.put("id", user.getId());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new SecurityException("Неверный логин или пароль");
        }
    }

    @GetMapping(value = "login")
    public ResponseEntity<Principal> googleAuth(@RequestBody Principal principal) {
        return new ResponseEntity<>(principal, HttpStatus.CREATED);
    }
}
