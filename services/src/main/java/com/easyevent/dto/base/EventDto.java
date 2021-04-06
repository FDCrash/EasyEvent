package com.easyevent.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    private long id;

    private String name;

    private Date startDate;

    private Date endDate;

    private String description;

    private int cost;

    private String createdAt;

    private String expiresAt;

    private Map<UUID, String> organizations;

    private Map<UUID, String> artists;

    private int summaryCost;
}
