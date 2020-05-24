package com.easyevent.dto.base;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ArtistDto extends UserDto {

    private String name;

    private String type;

    private String description;

    private Map<Long, String> events;

    private Map<Long, String> offers;

    @Builder(builderMethodName = "artistBuilder")
    public ArtistDto(UserDto userDto, String name, String type, String description, Map<Long, String> events, Map<Long, String> offers) {
        super(userDto.getId(), userDto.getLogin(), userDto.getPassword(), userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.isRegistration(), userDto.getRole(), userDto.getPhone());
        this.name = name;
        this.type = type;
        this.description = description;
        this.events = events;
        this.offers = offers;
    }

    //    public UserDto getUser(){
//        return UserDto.builder()
//                .id(getId())
//                .login(getLogin())
//                .password(getPassword())
//                .email(getEmail())
//                .firstName(getFirstName())
//                .lastName(getLastName())
//                .registration(isRegistration())
//                .role(getRole())
//                .phone(getPhone())
//                .build();
//    }
}
