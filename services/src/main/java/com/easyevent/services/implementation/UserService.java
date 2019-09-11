package com.easyevent.services.implementation;

import com.easyevent.converters.UserConverter;
import com.easyevent.dao.UserRepository;
import com.easyevent.dto.UserDto;
import com.easyevent.services.UserCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserCrud<UserDto> {

    private UserConverter userConverter;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserConverter userConverter, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public String add(UserDto userDto) {
        return userRepository.save(userConverter.convert(userDto)).getId().toString();
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }

    @Override
    public List<UserDto> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userEntity -> userConverter.convert(userEntity))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto get(UUID id) {
        return userConverter.convert(userRepository.findOne(id));
    }
}
