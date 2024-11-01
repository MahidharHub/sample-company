package com.greenbone.task.samplecompany.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Exception Handler for not finding any unassigned available computers
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AvailableComputersNotFoundException extends RuntimeException{

    public   AvailableComputersNotFoundException(String message){
        super(message);
    }
}
