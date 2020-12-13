package com.easyevent.configurations.security;

import com.easyevent.configurations.GoogleConfig;
import com.easyevent.details.jwt.JwtConfigurer;
import com.easyevent.details.jwt.JwtTokenProvider;
import com.easyevent.details.oauth.CustomUserInfoTokenServices;
import com.easyevent.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableSwagger2
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsServiceImpl;

    private final OAuth2ClientContext oAuth2ClientContext;

    private final GoogleConfig googleConfig;

    private final UserService userService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsServiceImpl, JwtTokenProvider jwtTokenProvider, OAuth2ClientContext oAuth2ClientContext, GoogleConfig googleConfig, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtTokenProvider = jwtTokenProvider;
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.googleConfig = googleConfig;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers( "/security/auth", "/security").permitAll()
                .antMatchers("/artists", "/events", "/offers", "/organizations").authenticated()
                .antMatchers("/users").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .csrf()
                .disable();
        http.httpBasic().disable();
        http.addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Bean
    public FilterRegistrationBean oAuth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(oAuth2ClientContextFilter);
        registration.setOrder(-200);
        return registration;
    }

    @Bean
    public AuthorizationCodeResourceDetails google() {
        AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();
        authorizationCodeResourceDetails.setAccessTokenUri(googleConfig.getAccessTokenUri());
        authorizationCodeResourceDetails.setClientId(googleConfig.getClientId());
        authorizationCodeResourceDetails.setClientSecret(googleConfig.getClientSecret());
        authorizationCodeResourceDetails.setUserAuthorizationUri(googleConfig.getUserAuthorizationUri());
        authorizationCodeResourceDetails.setClientAuthenticationScheme(AuthenticationScheme.form);
        authorizationCodeResourceDetails.setScope(googleConfig.getScope());
        return authorizationCodeResourceDetails;
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/security/login");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
        tokenServices.setRestTemplate(googleTemplate);
        googleFilter.setTokenServices(tokenServices);
        tokenServices.setUserService(userService);
        return googleFilter;
    }
}