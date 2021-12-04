package com.example.fitnessdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Object already exists")
public class ObjectAlreadyExistsException extends RuntimeException {


    public ObjectAlreadyExistsException() {
    }

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
