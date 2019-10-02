package com.easyevent.converters;

import com.easyevent.dto.base.ArtistDto;
import com.easyevent.entity.ArtistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtistConverter {

    private UserConverter userConverter;

    @Autowired
    public ArtistConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public ArtistDto convert(ArtistEntity artistEntity) {
        return ArtistDto.artistBuilder()
                .userDto(userConverter.convert(artistEntity.getUser()))
                .pseudonym(artistEntity.getPseudonym())
                .type(artistEntity.getType())
                .description(artistEntity.getDescription())
                .eventIds(artistEntity.getEventIds())
                .eventName(artistEntity.getEventNames())
                .offerIds(artistEntity.getOfferIds())
                .offerNames(artistEntity.getOfferNames())
                .build();
    }

    public ArtistEntity convert(ArtistDto artistDto) {
        return ArtistEntity.artistBuilder()
                .userEntity(userConverter.convert(artistDto.getUser()))
                .pseudonym(artistDto.getPseudonym())
                .type(artistDto.getType())
                .description(artistDto.getDescription())
//                .eventEntities(
//                        artistDto.getEventIds().stream()
//                                .map(a -> EventEntity.builder()
//                                        .id(a)
//                                        .build()
//                                )
//                                .collect(Collectors.toList())
//                )
//                .artistOfferEntities(artistDto.getOfferIds().stream()
//                        .map(a -> ArtistOfferEntity.builder()
//                                .id(a)
//                                .build()
//                        )
//                        .collect(Collectors.toList())
//                )
                .build();
    }

}
