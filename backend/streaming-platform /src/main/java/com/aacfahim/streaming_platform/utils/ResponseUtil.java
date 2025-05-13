package com.aacfahim.streaming_platform.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.aacfahim.streaming_platform.config.Constant.FAILED_STATUS;
import static com.aacfahim.streaming_platform.config.Constant.SUCCESS_STATUS;


@Slf4j
public class ResponseUtil {

    public ResponseUtil() {
    }

    public static<T> ResponseEntity<ApiResponse<T>> buildSuccessResponse(int statusCode, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(statusCode, SUCCESS_STATUS, message, data, null);
        return ResponseEntity.ok(response);
    }

    public static<T> ResponseEntity<ApiResponse<T>> buildErrorResponse(int statusCode, String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(statusCode, FAILED_STATUS, message, null, null);
        return ResponseEntity.status(status).body(response);
    }

    public static<T> ResponseEntity<ApiResponse<T>> buildErrorResponseWithData(int statusCode, String message, HttpStatus status, T data) {
        ApiResponse<T> response = new ApiResponse<>(statusCode, FAILED_STATUS, message, data, null);
        return ResponseEntity.status(status).body(response);
    }
}
