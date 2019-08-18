package com.easyevent.services.implementation;

import com.easyevent.dto.UserDto;
import com.easyevent.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CrudService<UserDto> {

    @Override
    public long add(UserDto userDto) {
        return 0;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto get(long id) {
        return null;
    }
}
