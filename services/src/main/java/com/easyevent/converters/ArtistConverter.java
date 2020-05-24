package com.easyevent.converters;

import com.easyevent.dto.base.ArtistDto;
import com.easyevent.entity.ArtistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArtistConverter {

    private UserConverter userConverter;

    @Autowired
    public ArtistConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public ArtistDto convert(ArtistEntity artistEntity) {
        Map<Long, String> events = new HashMap<>();
        artistEntity.getEventEntities().forEach(eventEntity -> {
            events.put(eventEntity.getId(), eventEntity.getName());
        });
        Map<Long, String> offers = new HashMap<>();
        artistEntity.getArtistOfferEntities().forEach(artistOfferEntity -> {
           offers.put(artistOfferEntity.getId(), artistOfferEntity.getName());
        });
        return ArtistDto.artistBuilder()
                .userDto(userConverter.convert(artistEntity.getUser()))
                .name(artistEntity.getPseudonym())
                .type(artistEntity.getType())
                .description(artistEntity.getDescription())
                .events(events)
                .offers(offers)
                .build();
    }

    public ArtistEntity convert(ArtistDto artistDto) {
        return ArtistEntity.artistBuilder()
                .userEntity(userConverter.convert(artistDto.getUser()))
                .pseudonym(artistDto.getName())
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
