package com.netcracker;

import com.easyevent.converters.ArtistConverter;
import com.easyevent.converters.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public UserConverter userConverter(){
        return new UserConverter();
    }

    @Bean
    public ArtistConverter artistConverter(){
        return new ArtistConverter(userConverter());
    }
}
