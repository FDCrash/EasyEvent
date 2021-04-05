package com.easyevent.converters;

import com.easyevent.dto.base.EventDto;
import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.EventEntity;
import com.easyevent.entity.OrganizationEntity;
import com.easyevent.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventConverter {

    public EventDto convert(EventEntity eventEntity) {
        Map<UUID, String> organizations = new HashMap<>();
        eventEntity.getOrganizationEntities().forEach(organizationEntity -> organizations.put(organizationEntity.getId(), organizationEntity.getName()));
        Map<UUID, String> artists = new HashMap<>();
        eventEntity.getArtistEntities().forEach(artistEntity -> artists.put(artistEntity.getId(), artistEntity.getPseudonym()));
        return EventDto.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .startDate(eventEntity.getStartDate())
                .endDate(eventEntity.getEndDate())
                .description(eventEntity.getDescription())
                .cost(eventEntity.getCost())
                .createdAt(eventEntity.getCreatedAt())
                .expiresAt(eventEntity.getExpiresAt())
                .organizations(organizations)
                .artists(artists)
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
                        eventDto.getOrganizations().keySet().stream()
                                .map(a ->
                                        OrganizationEntity.organizationBuilder()
                                                .userEntity(UserEntity.builder()
                                                        .id(a)
                                                        .build()
                                                )
                                                .build())
                                .collect(Collectors.toList()))
                .artistEntities(
                        eventDto.getArtists().keySet().stream()
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
