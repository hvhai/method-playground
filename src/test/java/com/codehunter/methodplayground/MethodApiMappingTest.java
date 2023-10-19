package com.codehunter.methodplayground;


import com.codehunter.methodplayground.client.method.dto.Entity;
import com.codehunter.methodplayground.client.method.dto.Response;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MethodApiMappingTest {
//    @Autowired
//    RestTemplate methodClient;

    @LocalServerPort
    private int serverPort;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
//            .options(wireMockConfig().dynamicPort())
            .build();


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("methodUrl", wireMockServer::baseUrl);
    }

//    @Test
    void testGetEntities() {
        String body = "{\n" +
                "    \"success\": true,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": \"ent_RnPECR7Gm99mc\",\n" +
                "            \"type\": \"individual\",\n" +
                "            \"individual\": {\n" +
                "                \"first_name\": \"Kevin\",\n" +
                "                \"last_name\": \"Doyle\",\n" +
                "                \"phone\": \"+15121231111\",\n" +
                "                \"dob\": \"2011-09-09\",\n" +
                "                \"email\": null,\n" +
                "                \"phone_verification_type\": \"method_verified\",\n" +
                "                \"phone_verification_timestamp\": \"2023-10-18T02:13:47.027Z\"\n" +
                "            },\n" +
                "            \"corporation\": null,\n" +
                "            \"receive_only\": null,\n" +
                "            \"capabilities\": [\n" +
                "                \"payments:send\",\n" +
                "                \"payments:receive\",\n" +
                "                \"data:sync\"\n" +
                "            ],\n" +
                "            \"available_capabilities\": [\n" +
                "                \"data:retrieve\"\n" +
                "            ],\n" +
                "            \"pending_capabilities\": [],\n" +
                "            \"error\": null,\n" +
                "            \"address\": {\n" +
                "                \"line1\": null,\n" +
                "                \"line2\": null,\n" +
                "                \"city\": null,\n" +
                "                \"state\": null,\n" +
                "                \"zip\": null\n" +
                "            },\n" +
                "            \"status\": \"active\",\n" +
                "            \"metadata\": null,\n" +
                "            \"created_at\": \"2023-10-18T02:13:47.032Z\",\n" +
                "            \"updated_at\": \"2023-10-18T02:13:47.032Z\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"ent_VBPQ7zj7gBBr7\",\n" +
                "            \"type\": \"individual\",\n" +
                "            \"individual\": {\n" +
                "                \"first_name\": \"Kevin\",\n" +
                "                \"last_name\": \"Doyle\",\n" +
                "                \"phone\": \"+15121231111\",\n" +
                "                \"dob\": \"2011-09-09\",\n" +
                "                \"email\": null,\n" +
                "                \"phone_verification_type\": \"method_verified\",\n" +
                "                \"phone_verification_timestamp\": \"2023-10-18T02:12:48.695Z\"\n" +
                "            },\n" +
                "            \"corporation\": null,\n" +
                "            \"receive_only\": null,\n" +
                "            \"capabilities\": [\n" +
                "                \"payments:send\",\n" +
                "                \"payments:receive\",\n" +
                "                \"data:sync\"\n" +
                "            ],\n" +
                "            \"available_capabilities\": [\n" +
                "                \"data:retrieve\"\n" +
                "            ],\n" +
                "            \"pending_capabilities\": [],\n" +
                "            \"error\": null,\n" +
                "            \"address\": {\n" +
                "                \"line1\": null,\n" +
                "                \"line2\": null,\n" +
                "                \"city\": null,\n" +
                "                \"state\": null,\n" +
                "                \"zip\": null\n" +
                "            },\n" +
                "            \"status\": \"active\",\n" +
                "            \"metadata\": null,\n" +
                "            \"created_at\": \"2023-10-18T02:12:48.703Z\",\n" +
                "            \"updated_at\": \"2023-10-18T02:12:48.703Z\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"ent_yKVLdN9GL894U\",\n" +
                "            \"type\": \"individual\",\n" +
                "            \"individual\": {\n" +
                "                \"first_name\": \"Kevin\",\n" +
                "                \"last_name\": \"Doyle\",\n" +
                "                \"phone\": \"+15121231111\",\n" +
                "                \"dob\": null,\n" +
                "                \"email\": null,\n" +
                "                \"phone_verification_type\": \"method_verified\",\n" +
                "                \"phone_verification_timestamp\": \"2023-10-17T09:40:11.987Z\"\n" +
                "            },\n" +
                "            \"corporation\": null,\n" +
                "            \"receive_only\": null,\n" +
                "            \"capabilities\": [\n" +
                "                \"payments:send\",\n" +
                "                \"payments:receive\",\n" +
                "                \"data:sync\",\n" +
                "                \"data:retrieve\"\n" +
                "            ],\n" +
                "            \"available_capabilities\": [],\n" +
                "            \"pending_capabilities\": [],\n" +
                "            \"error\": null,\n" +
                "            \"address\": {\n" +
                "                \"line1\": null,\n" +
                "                \"line2\": null,\n" +
                "                \"city\": null,\n" +
                "                \"state\": null,\n" +
                "                \"zip\": null\n" +
                "            },\n" +
                "            \"status\": \"active\",\n" +
                "            \"metadata\": null,\n" +
                "            \"created_at\": \"2023-10-17T09:40:11.994Z\",\n" +
                "            \"updated_at\": \"2023-10-17T10:15:48.413Z\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"ent_yxxCEUCYAHydR\",\n" +
                "            \"type\": \"individual\",\n" +
                "            \"individual\": {\n" +
                "                \"first_name\": \"Kevin\",\n" +
                "                \"last_name\": \"Doyle\",\n" +
                "                \"phone\": \"+16505555555\",\n" +
                "                \"dob\": \"1997-03-18\",\n" +
                "                \"email\": \"kevin.doyle@gmail.com\",\n" +
                "                \"phone_verification_type\": \"method_verified\",\n" +
                "                \"phone_verification_timestamp\": \"2023-10-17T08:02:25.566Z\"\n" +
                "            },\n" +
                "            \"corporation\": null,\n" +
                "            \"receive_only\": null,\n" +
                "            \"capabilities\": [],\n" +
                "            \"available_capabilities\": [],\n" +
                "            \"pending_capabilities\": [\n" +
                "                \"payments:send\",\n" +
                "                \"payments:receive\",\n" +
                "                \"data:sync\",\n" +
                "                \"data:retrieve\"\n" +
                "            ],\n" +
                "            \"error\": null,\n" +
                "            \"address\": {\n" +
                "                \"line1\": \"3300 N Interstate 35\",\n" +
                "                \"line2\": null,\n" +
                "                \"city\": \"Austin\",\n" +
                "                \"state\": \"TX\",\n" +
                "                \"zip\": \"78705\"\n" +
                "            },\n" +
                "            \"status\": \"incomplete\",\n" +
                "            \"metadata\": null,\n" +
                "            \"created_at\": \"2023-10-17T08:02:25.572Z\",\n" +
                "            \"updated_at\": \"2023-10-17T08:02:25.572Z\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"message\": null\n" +
                "}";
        String body2 = "{\n" +
                "    \"success\": true,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": \"ent_RnPECR7Gm99mc\",\n" +
                "            \"type\": \"individual\",\n" +
                "            \"individual\": {\n" +
                "                \"first_name\": \"Kevin\",\n" +
                "                \"last_name\": \"Doyle\",\n" +
                "                \"phone\": \"+15121231111\",\n" +
                "                \"dob\": \"2011-09-09\",\n" +
                "                \"email\": null,\n" +
                "                \"phone_verification_type\": \"method_verified\",\n" +
                "                \"phone_verification_timestamp\": \"2023-10-18T02:13:47.027Z\"\n" +
                "            },\n" +
                "            \"corporation\": null,\n" +
                "            \"receive_only\": null,\n" +
                "            \"capabilities\": [\n" +
                "                \"payments:send\",\n" +
                "                \"payments:receive\",\n" +
                "                \"data:sync\"\n" +
                "            ],\n" +
                "            \"available_capabilities\": [\n" +
                "                \"data:retrieve\"\n" +
                "            ],\n" +
                "            \"pending_capabilities\": [],\n" +
                "            \"error\": null,\n" +
                "            \"address\": {\n" +
                "                \"line1\": null,\n" +
                "                \"line2\": null,\n" +
                "                \"city\": null,\n" +
                "                \"state\": null,\n" +
                "                \"zip\": null\n" +
                "            },\n" +
                "            \"status\": \"active\",\n" +
                "            \"metadata\": null,\n" +
                "            \"created_at\": \"2023-10-18T02:13:47.032Z\",\n" +
                "            \"updated_at\": \"2023-10-18T02:13:47.032Z\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"message\": null\n" +
                "}";

        wireMockServer.stubFor(
                WireMock.get("/entities")
                        .withHost(equalTo("dev.methodfi.com"))
                        .withHeader("Authorization", equalTo("Bearer sk_JicM3zMVzyB3TAfCUHm8dhP6"))
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

    @AfterEach
    void afterEach() {
        wireMockServer.resetAll();
    }
}
