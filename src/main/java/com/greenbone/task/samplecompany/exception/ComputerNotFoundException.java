package com.greenbone.task.samplecompany.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handler for not finding any computer
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComputerNotFoundException extends RuntimeException {

    public   ComputerNotFoundException(String message){
        super(message);
    }
}
