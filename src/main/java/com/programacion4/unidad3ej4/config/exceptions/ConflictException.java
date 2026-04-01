package com.programacion4.unidad3ej4.config.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}