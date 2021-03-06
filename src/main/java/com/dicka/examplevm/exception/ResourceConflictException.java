package com.dicka.examplevm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String msg, Throwable cause){
        super(msg, cause);
    }

    public ResourceConflictException(String msg){
        super(msg);
    }
}
