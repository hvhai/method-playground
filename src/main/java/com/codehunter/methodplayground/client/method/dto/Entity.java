package com.codehunter.methodplayground.client.method.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
    String id;
    String type;
    List<String> capabilities;
}
