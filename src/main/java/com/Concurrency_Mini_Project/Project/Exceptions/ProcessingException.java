package com.Concurrency_Mini_Project.Project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProcessingException extends RuntimeException{

    public ProcessingException(String message) {
        super(message);
    }
}
