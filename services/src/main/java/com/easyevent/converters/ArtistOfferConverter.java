package com.easyevent.converters;

import com.easyevent.dto.ArtistOfferDto;
import com.easyevent.entity.ArtistEntity;
import com.easyevent.entity.ArtistOfferEntity;
import com.easyevent.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ArtistOfferConverter {

    public ArtistOfferDto convert(ArtistOfferEntity artistOfferEntity){
        return ArtistOfferDto.builder()
                .id(artistOfferEntity.getId())
                .description(artistOfferEntity.getDescription())
                .cost(artistOfferEntity.getCost())
                .artistId(artistOfferEntity.getArtistEntity().getId())
                .artistName(artistOfferEntity.getArtistEntity().getPseudonym())
                .build();
    }

    public ArtistOfferEntity convert(ArtistOfferDto artistOfferDto){
        return ArtistOfferEntity.builder()
                .id(artistOfferDto.getId())
                .description(artistOfferDto.getDescription())
                .cost(artistOfferDto.getCost())
                .artistEntity(
                        ArtistEntity.artistBuilder()
                        .userEntity(
                                UserEntity.builder()
                                .id(artistOfferDto.getArtistId())
                                .build()
                        )
                        .build()
                )
                .build();
    }

}
