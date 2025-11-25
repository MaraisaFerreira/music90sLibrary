package com.study.mf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StorageFailException extends RuntimeException {
    public StorageFailException(String message) {
        super(message);
    }
}
