package com.backend.Manager_restaurant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PromotionNotFoundException extends RuntimeException{
    private static  final long serialVersionUID = 1L;

    public PromotionNotFoundException(String ex) {
        super(ex);
    }
}
