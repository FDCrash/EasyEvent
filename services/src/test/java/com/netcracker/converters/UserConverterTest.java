package com.netcracker.converters;

import com.easyevent.converters.UserConverter;
import com.easyevent.dto.base.RoleDto;
import com.easyevent.dto.base.UserDto;
import com.easyevent.entity.Role;
import com.easyevent.entity.UserEntity;
import com.netcracker.TestConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class UserConverterTest {

    @Autowired
    private UserConverter userConverter;

    private UserDto userDto;
    private UserEntity userEntity;
    private UserDto userDtoExpected;
    private UserEntity userEntityExpected;

    @Before
    public void setUp(){
        userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .login("test")
                .password("test")
                .email("test")
                .firstName("test")
                .lastName("test")
                .registration(true)
                .role(RoleDto.ARTIST)
                .phone("test")
                .build();
        userEntity = UserEntity.builder()
                .id(UUID.randomUUID())
                .login("test")
                .password("test")
                .email("test")
                .firstName("test")
                .lastName("test")
                .registration(false)
                .role(Role.ARTIST_ORGANIZATION)
                .phone("test")
                .build();
        userDtoExpected = UserDto.builder()
                .id(userEntity.getId())
                .login("test")
                .password("test")
                .email("test")
                .firstName("test")
                .lastName("test")
                .registration(false)
                .role(RoleDto.ARTIST_ORGANIZATION)
                .phone("test")
                .build();
        userEntityExpected = UserEntity.builder()
                .id(userDto.getId())
                .login("test")
                .password("test")
                .email("test")
                .firstName("test")
                .lastName("test")
                .registration(true)
                .role(Role.ARTIST)
                .phone("test")
                .build();

    }

    @After
    public void tearDown(){
        userDto = null;
        userEntity = null;
        userDtoExpected = null;
        userEntityExpected = null;
    }

    @Test
    public void toDto(){
        UserDto userDtoActual = userConverter.convert(userEntity);
        Assert.assertEquals(userDtoActual,userDtoExpected);

    }

    @Test
    public void toEntity(){
        UserEntity userEntityActual = userConverter.convert(userDto);
        Assert.assertEquals(userEntityActual,userEntityExpected);

    }
}
