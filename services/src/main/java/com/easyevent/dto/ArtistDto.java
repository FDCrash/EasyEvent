package com.easyevent.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArtistDto extends UserDto{

    private UUID id;

    private String pseudonym;

    private String type;

    private String description;

    private List<Long> eventIds;

    private List<String> eventName;

    private List<Long> offerIds;

    private List<String> offerNames;

    @Builder(builderClassName = "ArtistBuilder")
    public ArtistDto(UserDto userDto, String pseudonym, String type, String description, List<Long> eventIds, List<String> eventName, List<Long> offerIds, List<String> offerNames) {
        super(userDto.getId(), userDto.getLogin(), userDto.getPassword(), userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.isRegistration(), userDto.getRole(), userDto.getPhone());
        this.id = userDto.getId();
        this.pseudonym = pseudonym;
        this.type = type;
        this.description = description;
        this.eventIds.addAll(eventIds);
        this.eventName.addAll(eventName);
        this.offerIds.addAll(offerIds);
        this.offerNames.addAll(offerNames);
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
