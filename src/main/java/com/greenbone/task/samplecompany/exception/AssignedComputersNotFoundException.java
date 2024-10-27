package com.greenbone.task.samplecompany.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Exception Handler for not finding any assigned computers
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssignedComputersNotFoundException extends  RuntimeException{

    public   AssignedComputersNotFoundException(String message){
        super(message);
    }
}
