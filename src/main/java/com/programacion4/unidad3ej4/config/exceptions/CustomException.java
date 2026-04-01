package com.programacion4.unidad3ej4.config.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus status;
    private final List<String> errors;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.errors = List.of(message);
    }

    public CustomException(String message, List<String> errors, HttpStatus status) {
        super(message);
        this.status = status;
        this.errors = errors;
    }
}