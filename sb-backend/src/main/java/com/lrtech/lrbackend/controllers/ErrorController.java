package com.lrtech.lrbackend.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController {

    // Validation errors //
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleValidationErrors(ConstraintViolationException e){
        List<Map<String, String>> errorList = e.getConstraintViolations().stream()
                .map(error -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(error.getPropertyPath().toString(), error.getMessage());
                    System.out.println(errorMap.toString());
                    return errorMap;
                }).toList();
        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity handleJPAViolation(TransactionSystemException e){
        System.out.println(e);
        System.out.println(e.getCause().getCause());
        if(e.getCause().getCause() instanceof ConstraintViolationException ve) {
            return handleValidationErrors(ve);
        }
        return ResponseEntity.badRequest().build();
    }

    // Method argument binding errors //
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException e) {
        System.out.println("method bind error");
        List<Map<String, String>> errorList = e.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

    // Stripe Errors //

}
