package com.codehunter.methodplayground.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class MethodClientConfig {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    @Value("${app.method.api.token}")
    private String authorizationHeaderValue;

    @Bean
    public RestTemplate methodClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new CustomHeaderInterceptor(AUTHORIZATION_HEADER_NAME, "Bearer " + authorizationHeaderValue)));
        return restTemplate;
    }
}
