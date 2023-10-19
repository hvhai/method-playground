package com.codehunter.methodplayground.client.method.dto;

import lombok.Data;

@Data
public class WebHookEvent {
    String id;
    String type;
    String op;
}
