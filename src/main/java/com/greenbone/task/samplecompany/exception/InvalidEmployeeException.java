package com.greenbone.task.samplecompany.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handler for providing invalid employee details
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmployeeException  extends RuntimeException{

    public   InvalidEmployeeException(String message){
        super(message);
    }
}
