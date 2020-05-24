package com.easyevent.converters;

import com.easyevent.dto.base.ArtistDto;
import com.easyevent.dto.base.OrganizationDto;
import com.easyevent.dto.base.UserRegisterDto;
import org.springframework.stereotype.Component;

@Component
public class UserToConverter {

    public ArtistDto convertToArtist(UserRegisterDto userRegisterDto) {
        return ArtistDto.artistBuilder()
                .userDto(userRegisterDto.getUser())
                .name(userRegisterDto.getName())
                .type(userRegisterDto.getType())
                .description(userRegisterDto.getDescription())
                .build();
    }

    public OrganizationDto convertToOrganization(UserRegisterDto userRegisterDto) {
        return OrganizationDto.organizationBuilder()
                .userDto(userRegisterDto.getUser())
                .name(userRegisterDto.getName())
                .type(userRegisterDto.getType())
                .description(userRegisterDto.getDescription())
                .build();
    }
}
