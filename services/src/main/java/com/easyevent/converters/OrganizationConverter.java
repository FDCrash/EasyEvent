package com.easyevent.converters;

import com.easyevent.dto.base.OrganizationDto;
import com.easyevent.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConverter {
    private UserConverter userConverter;

    @Autowired
    public OrganizationConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public OrganizationDto convert(OrganizationEntity organizationEntity) {
        return OrganizationDto.organizationBuilder()
                .userDto(userConverter.convert(organizationEntity.getUser()))
                .name(organizationEntity.getName())
                .type(organizationEntity.getType())
                .description(organizationEntity.getDescription())
                .eventIds(organizationEntity.getEventIds())
                .eventName(organizationEntity.getEventNames())
                .build();
    }

    public OrganizationEntity convert(OrganizationDto organizationDto) {
        return OrganizationEntity.organizationBuilder()
                .userEntity(userConverter.convert(organizationDto.getUser()))
                .name(organizationDto.getName())
                .type(organizationDto.getType())
                .description(organizationDto.getDescription())
//                .eventEntities(
//                        artistDto.getEventIds().stream()
//                                .map(a -> EventEntity.builder()
//                                        .id(a)
//                                        .build()
//                                )
//                                .collect(Collectors.toList())
//                )
                .build();
    }
}
