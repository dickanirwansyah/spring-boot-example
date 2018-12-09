package com.dicka.examplevm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotAcceptable extends RuntimeException{

    public ResourceNotAcceptable(String msg, Throwable cause){
        super(msg, cause);
    }

    public ResourceNotAcceptable(String msg){
        super(msg);
    }
}
