package com.easyevent.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class EventDto {

    private long id;

    private String name;

    private Date startDate;

    private Date endDate;

    private String description;

    private int cost;

    private String createdAt;

    private String expiresAt;

    private List<UUID> organizationIds;

    private List<String> organizationNames;

    private List<UUID> artistIds;

    private List<String> artistNames;

}
