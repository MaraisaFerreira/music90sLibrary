package com.study.mf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> genericErrors(Exception ex){
        return ResponseEntity.internalServerError().body(new ResponseException(
                Instant.now().toEpochMilli(),
                ex.getMessage()
        ));
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ResponseException> resourceNotFound(CustomNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseException(Instant.now().toEpochMilli(), ex.getMessage()));
    }
}
