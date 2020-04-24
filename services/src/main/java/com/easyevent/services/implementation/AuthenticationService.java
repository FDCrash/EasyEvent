package com.easyevent.services.implementation;

import com.easyevent.dao.UserRepository;
import com.easyevent.dto.base.BaseLogin;
import com.easyevent.dto.base.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RoleDto getRoleByCredentials(BaseLogin baseLogin) {
        if (!userRepository.existsByLogin(baseLogin.getLogin())) {
            return null;
        }
        return RoleDto.valueOf(userRepository.getUserEntityByLoginAndPassword(baseLogin.getLogin(), baseLogin.getPassword()).getRole().name());
    }

}
