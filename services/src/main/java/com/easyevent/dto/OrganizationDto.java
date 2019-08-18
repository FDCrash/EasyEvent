package com.easyevent.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrganizationDto extends UserDto {

    private UUID id;

    private String name;

    private String type;

    private String description;

    private List<EventDto> eventEntities;

    private List<UUID> eventIds;

    private List<String> eventName;

}
