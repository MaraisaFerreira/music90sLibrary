package com.study.mf.exceptions;

public record ExceptionResponseDto(
    Long timestamp,
    String message,
    String method,
    String details
) {
}
