package com.easyevent.dto.base;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserRegisterDto extends UserDto {

    private String name;

    private String type;

    private String description;
}
