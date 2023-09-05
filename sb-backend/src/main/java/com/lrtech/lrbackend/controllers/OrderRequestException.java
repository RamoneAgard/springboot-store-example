package com.lrtech.lrbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid or Unformatted Order Request")
public class OrderRequestException extends RuntimeException{
    public OrderRequestException(){}

    public OrderRequestException(String message){
        super(message);
    }

    public OrderRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRequestException(Throwable cause) {
        super(cause);
    }

    public OrderRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
