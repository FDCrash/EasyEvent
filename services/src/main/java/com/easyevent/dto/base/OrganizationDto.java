package com.easyevent.dto.base;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrganizationDto extends UserDto {

    private String name;

    private String type;

    private String description;

    private List<Long> eventIds;

    private List<String> eventName;

    @Builder(builderMethodName = "organizationBuilder")
    public OrganizationDto(UserDto userDto, String name, String type, String description, List<Long> eventIds, List<String> eventName) {
        super(userDto.getId(), userDto.getLogin(), userDto.getPassword(), userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), userDto.isRegistration(), userDto.getRole(), userDto.getPhone());
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventIds = eventIds;
        this.eventName = eventName;
    }
}
