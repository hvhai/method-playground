package com.codehunter.methodplayground.controller;

import com.codehunter.methodplayground.client.method.dto.Entity;
import com.codehunter.methodplayground.client.method.dto.Response;
import com.codehunter.methodplayground.client.method.dto.WebHookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/method")
@RequiredArgsConstructor
@Slf4j
public class MethodGatewayController {
    @Value("${app.method.url}")
    private String methodUrl;
    private final RestTemplate methodClient;

    @GetMapping("/entities")
    public Response<List<Entity>> getAllEntities() {
        ParameterizedTypeReference<Response<List<Entity>>> responseType = new ParameterizedTypeReference<Response<List<Entity>>>() {
        };
        ResponseEntity<Response<List<Entity>>> response = methodClient.exchange(methodUrl + "/entities", HttpMethod.GET, null, responseType);
        Response<List<Entity>> body = response.getBody();
        return body;
    }

    @PostMapping("/webhook")
    @ResponseStatus(HttpStatus.OK)
    public void webhook(@RequestBody WebHookEvent event) {
        log.info("Receive webhook event : {}", event);
    }
}
