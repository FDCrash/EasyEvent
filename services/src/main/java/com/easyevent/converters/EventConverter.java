package com.easyevent.converters;

import com.easyevent.dto.base.EventDto;
import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.EventEntity;
import com.easyevent.entity.OrganizationEntity;
import com.easyevent.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventConverter {

    public EventDto convert(EventEntity eventEntity) {
        return EventDto.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .startDate(eventEntity.getStartDate())
                .endDate(eventEntity.getEndDate())
                .description(eventEntity.getDescription())
                .cost(eventEntity.getCost())
                .createdAt(eventEntity.getCreatedAt())
                .expiresAt(eventEntity.getExpiresAt())
                .organizationIds(eventEntity.getOrganizationIds())
                .organizationNames(eventEntity.getOrganizationNames())
                .artistIds(eventEntity.getArtistIds())
                .artistPseudonym(eventEntity.getArtistPseudonym())
                .build();
    }

    public EventEntity convert(EventDto eventDto) {
        return EventEntity.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startDate(eventDto.getStartDate())
                .endDate(eventDto.getEndDate())
                .description(eventDto.getDescription())
                .cost(eventDto.getCost())
                .createdAt(eventDto.getCreatedAt())
                .expiresAt(eventDto.getExpiresAt())
                .organizationEntities(
                        eventDto.getOrganizationIds().stream()
                                .map(a ->
                                    OrganizationEntity.organizationBuilder()
                                            .userEntity(UserEntity.builder()
                                                    .id(a)
                                                    .build()
                                            )
                                            .build())
                                .collect(Collectors.toList()))
                .artistEntities(
                        eventDto.getArtistIds().stream()
                                .map(a ->
                                        ArtistEntity.artistBuilder()
                                                .userEntity(UserEntity.builder()
                                                        .id(a)
                                                        .build()
                                                )
                                                .build())
                                .collect(Collectors.toList()))
                .build();
    }
}
