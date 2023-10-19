package com.codehunter.methodplayground.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class WebHookEventDto {
    private Long id;
    private String data;
    private ZonedDateTime createAt;
}
