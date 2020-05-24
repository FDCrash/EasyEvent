package com.easyevent.configurations;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "security.oauth2.client")
public class GoogleConfig {

    @Value("client-secret")
    private String clientSecret;

    @Value("access-token-uri")
    private String accessTokenUri;

    @Value("user-authorization-uri")
    private String userAuthorizationUri;

    @Value("scope")
    private List<String> scope;

    @Value("client-id")
    private String clientId;

}
