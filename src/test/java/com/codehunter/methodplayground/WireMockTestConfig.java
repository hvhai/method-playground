package com.codehunter.methodplayground;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@TestConfiguration
public class WireMockTestConfig {
    @Bean
    public static WireMockServer wireMockServer() {
        return  new WireMockServer(WireMockSpring.options().dynamicPort());
    }
    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("app.method.url", wireMockServer()::baseUrl);
        registry.add("app.method.api.token", () ->"mock-api-token");
    }
}
