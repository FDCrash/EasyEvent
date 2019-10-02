package com.easyevent.controllers;

import com.easyevent.dto.base.UserDto;
import com.easyevent.services.implementation.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api
@RestController
@RequestMapping("/users")
public class UserController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @ApiOperation(value = "Add user", nickname = "UserController.addUser")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        Map id = Collections.singletonMap("id", userService.add(userDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets all users", nickname = "UserController.getAllUsers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAll();
        if (CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific user", nickname = "UserController.getUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") UUID id) {
        UserDto user = userService.get(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @ApiOperation(value = "Update user", nickname = "UserController.updateUser")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "User update")})
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> updateUser(@RequestBody UserFormDTO userFormDTO) {
//        UserDTO user = userService.get(userFormDTO.getId());
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        UserDTO userDTO = UserDTO.builder()
//                .id(userFormDTO.getId())
//                .login(userFormDTO.getLogin())
//                .password(passwordEncoder.encode(userFormDTO.getPassword()))
//                .name(userFormDTO.getName())
//                .roleDTO(user.getRoleDTO())
//                .build();
//        long id = userService.add(userDTO);
//        return new ResponseEntity<>(id, HttpStatus.OK);
//    }

    @ApiOperation(value = "Delete user", nickname = "UserController.deleteUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User is deleted")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
