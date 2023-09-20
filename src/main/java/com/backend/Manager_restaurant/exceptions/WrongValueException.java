package com.backend.Manager_restaurant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongValueException extends RuntimeException{
    private static  final long serialVersionUID = 1L;

    public WrongValueException(String ex) {
        super(ex);
    }
}
