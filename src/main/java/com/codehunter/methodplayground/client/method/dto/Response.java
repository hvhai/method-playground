package com.codehunter.methodplayground.client.method.dto;

import lombok.Data;

@Data
public class Response<T> {
    String success;
    T data;
    String message;
}
