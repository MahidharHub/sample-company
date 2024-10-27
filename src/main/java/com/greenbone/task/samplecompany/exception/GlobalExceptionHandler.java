package com.greenbone.task.samplecompany.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ComputerNotFoundException.class)
    public final ResponseEntity<Object> handleComputerNotFoundExceptions(ComputerNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false) + "  not found");

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    public final ResponseEntity<Object> handleInvalidEmployeeExceptions(InvalidEmployeeException ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false) + "  not found");

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }



}
