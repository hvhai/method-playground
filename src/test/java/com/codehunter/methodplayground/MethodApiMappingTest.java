package com.codehunter.methodplayground;


import com.codehunter.methodplayground.client.method.dto.Entity;
import com.codehunter.methodplayground.client.method.dto.Response;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import wiremock.org.apache.commons.io.IOUtils;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@SpringBootTest(webEnvironment = RANDOM_PORT,
        properties = {"spring.main.lazy-initialization=true"})
@AutoConfigureWireMock
@DirtiesContext(classMode = BEFORE_CLASS)
public class MethodApiMappingTest {
    @Autowired
    RestTemplate methodClient;

    @LocalServerPort
    private int serverPort;

    public static WireMockServer wireMockServer = new WireMockServer(WireMockSpring.options().dynamicPort());


    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("app.method.url", wireMockServer::baseUrl);
    }

    @BeforeAll
    static void setupClass() {
        wireMockServer.start();
    }

    @AfterEach
    void after() {
        wireMockServer.resetAll();
    }

    @AfterAll
    static void clean() {
        wireMockServer.shutdown();
    }

    @Test
    void testGetEntities() throws Exception {
        ClassPathResource resource = new ClassPathResource("wiremock-data/method-entities-response.json");
        String body2 = IOUtils.toString(resource.getInputStream(), "UTF-8");

        wireMockServer.stubFor(
                WireMock.get("/entities")
                        .withHeader("Authorization", equalTo("Bearer unittest-method-api-token"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(body2))
        );


        ParameterizedTypeReference<Response<List<Entity>>> responseType = new ParameterizedTypeReference<Response<List<Entity>>>() {
        };
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.exchange("http://localhost:" + serverPort + "/api/method/entities", HttpMethod.GET, null, responseType);
        Assertions.assertNotNull(response);
    }


}
