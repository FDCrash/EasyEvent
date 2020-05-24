package com.easyevent.services.implementation;

import com.easyevent.converters.UserConverter;
import com.easyevent.converters.UserToConverter;
import com.easyevent.dao.UserRepository;
import com.easyevent.details.UserDetailsImpl;
import com.easyevent.details.jwt.JwtTokenProvider;
import com.easyevent.dto.base.RoleDto;
import com.easyevent.dto.base.UserDto;
import com.easyevent.dto.base.UserRegisterDto;
import com.easyevent.services.UserCrud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserCrud<UserDto> {

    private UserConverter userConverter;
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private ArtistService artistService;
    private OrganizationService organizationService;
    private UserToConverter userToConverter;

    @Autowired
    public UserService(UserConverter userConverter, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider, OrganizationService organizationService, ArtistService artistService, UserToConverter userToConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.artistService = artistService;
        this.organizationService = organizationService;
        this.userToConverter = userToConverter;
    }

    @Override
    public String add(UserDto userDto) {
        return String.valueOf(userRepository.save(userConverter.convert(userDto)).getId());
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> userConverter.convert(userEntity))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto get(UUID id) {
        return userConverter.convert(userRepository.findOne(id));
    }

    public UserDto getCurrent() {
        UUID userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return userConverter.convert(userRepository.findOne(userId));
    }

    @Override
    public UserDto getByLogin(String login) {
        return userConverter.convert(userRepository.findByLogin(login));
    }

    //!!!Дописать подтверждение почтой
    public String register(UserRegisterDto userDto) {
        if (userRepository.existsByLogin(userDto.getLogin())) {
            return null;
        }
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setRegistration(true);
        String id = null;
        if (RoleDto.ARTIST.equals(userDto.getRole())) {
            id = artistService.add(userToConverter.convertToArtist(userDto));
        } else if (userDto.getRole().equals(RoleDto.ORGANIZATION)) {
            id = organizationService.add(userToConverter.convertToOrganization(userDto));
        } else {
            id = add(userDto);
        }
        log.info("User with id {} successfully registered", id);

        return id;
    }
}
