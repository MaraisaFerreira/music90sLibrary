package com.study.mf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> genericErrors(Exception ex, WebRequest request) {
        String method = null;
        if (request instanceof ServletWebRequest) {
            method = ((ServletWebRequest) request).getHttpMethod().name();
        }

        return ResponseEntity.internalServerError().body(
            new ExceptionResponseDto(
                Instant.now().toEpochMilli(),
                ex.getMessage(),
                method,
                request.getDescription(false)
            ));
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> resourceNotFound(CustomNotFoundException ex,
                                                                 WebRequest request) {
        String method = null;
        if (request instanceof ServletWebRequest) {
            method = ((ServletWebRequest) request).getHttpMethod().name();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ExceptionResponseDto(
                Instant.now().toEpochMilli(),
                ex.getMessage(),
                method,
                request.getDescription(false)
            ));
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ExceptionResponseDto> badRequestHandler(CustomBadRequestException ex,
                                                                  WebRequest request) {
        String method = null;
        if (request instanceof ServletWebRequest) {
            method = ((ServletWebRequest) request).getHttpMethod().name();
        }

        return ResponseEntity.badRequest().body(
            new ExceptionResponseDto(
                Instant.now().toEpochMilli(),
                ex.getMessage(),
                method,
                request.getDescription(false)
            ));
    }
}
