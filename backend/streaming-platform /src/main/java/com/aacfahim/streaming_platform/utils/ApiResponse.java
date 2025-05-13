package com.aacfahim.streaming_platform.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {
    private int statusCode;
    private String status;
    private String message;
    private T data;
    private Object metadata;
}
