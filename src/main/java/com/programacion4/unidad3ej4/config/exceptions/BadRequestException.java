package com.programacion4.unidad3ej4.config.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException(String message) {
        super(message, List.of(message), HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, List<String> errors) {
        super(message, errors, HttpStatus.BAD_REQUEST);
    }
}