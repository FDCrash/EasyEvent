package com.easyevent.dto;

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

    private List<UUID> eventIds;

    private List<String> eventName;


}
