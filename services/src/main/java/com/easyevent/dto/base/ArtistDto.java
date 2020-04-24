package com.easyevent.dto.base;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ArtistDto extends UserDto {

    private String pseudonym;

    private String type;

    private String description;

    private List<Long> eventIds;

    private List<String> eventName;

    private List<Long> offerIds;

    private List<String> offerNames;

    @Builder(builderMethodName = "artistBuilder")
    public ArtistDto(UserDto userDto, String pseudonym, String type, String description, List<Long> eventIds, List<String> eventName, List<Long> offerIds, List<String> offerNames) {
        super(userDto.getId(), userDto.getLogin(), userDto.getPassword(), userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.isRegistration(), userDto.getRole(), userDto.getPhone());
        this.pseudonym = pseudonym;
        this.type = type;
        this.description = description;
        this.eventIds = eventIds;
        this.eventName = eventName;
        this.offerIds = offerIds;
        this.offerNames = offerNames;
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
