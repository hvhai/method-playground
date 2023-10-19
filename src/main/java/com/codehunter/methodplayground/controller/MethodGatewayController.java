package com.codehunter.methodplayground.controller;

import com.codehunter.methodplayground.client.method.dto.Entity;
import com.codehunter.methodplayground.client.method.dto.Response;
import com.codehunter.methodplayground.client.method.dto.WebHookEvent;
import com.codehunter.methodplayground.dto.WebHookEventDto;
import com.codehunter.methodplayground.entity.H2WebHookEvent;
import com.codehunter.methodplayground.respository.WebHookEventRepository;
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

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/method")
@RequiredArgsConstructor
@Slf4j
public class MethodGatewayController {
    @Value("${app.method.url}")
    private String methodUrl;
    private final RestTemplate methodClient;
    private final WebHookEventRepository webHookEventRepository;

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
//        ofInstant(Instant.now(), ZoneOffset.UTC)
        var h2Event = H2WebHookEvent.builder().data(event.toString()).createAt(ZonedDateTime.now().toInstant()).build();
        webHookEventRepository.save(h2Event);
    }

    @GetMapping("/webhook-events")
    public List<WebHookEventDto> getAllEvent() {
        var eventList = webHookEventRepository.findAll();
        return eventList.stream()
                .map(event ->
                        WebHookEventDto.builder()
                                .id(event.getId())
                                .data(event.getData())
                                .createAt(ZonedDateTime.ofInstant(event.getCreateAt(), ZoneOffset.UTC))
                                .build())
                .collect(Collectors.toList());
    }
}
