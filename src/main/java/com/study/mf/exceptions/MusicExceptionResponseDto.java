package com.study.mf.exceptions;

public record MusicExceptionResponseDto(
    Long timestamp,
    String message,
    String method,
    String details
) {
}
