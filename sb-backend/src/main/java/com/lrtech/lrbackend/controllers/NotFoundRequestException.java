package com.lrtech.lrbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Value/Resource Not Found")
public class NotFoundRequestException extends RuntimeException{
    public NotFoundRequestException() {
    }

    public NotFoundRequestException(String message) {
        super(message);
    }

    public NotFoundRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundRequestException(Throwable cause) {
        super(cause);
    }

    public NotFoundRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
