package com.easyevent.dto.base;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ArtistOfferDto {

    private long id;

    private String description;

    private int cost;

    private UUID artistId;

    private String artistName;

}
