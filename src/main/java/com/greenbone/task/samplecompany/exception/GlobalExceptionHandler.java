package com.greenbone.task.samplecompany.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


/**
 * Global exception handler for handling all the exceptions in the application
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Generic method to handle all exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to handle ComputerNotFoundException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ComputerNotFoundException.class)
    public final ResponseEntity<Object> handleComputerNotFoundExceptions(ComputerNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false) + "  not found");

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Method to handle AssignedComputersNotFoundException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(AssignedComputersNotFoundException.class)
    public final ResponseEntity<Object> handleAssignedComputersNotFoundExceptions(AssignedComputersNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false) + "  not found");

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    /**
     * Method to handle AvailableComputersNotFoundException
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(AvailableComputersNotFoundException.class)
    public final ResponseEntity<Object> handleAvailableComputersNotFoundExceptions(AvailableComputersNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false) + "  not found");

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Method to handle handleInvalidEmployeeExceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(InvalidEmployeeException.class)
    public final ResponseEntity<Object> handleInvalidEmployeeExceptions(InvalidEmployeeException ex, WebRequest request) {

        ExceptionResponse exceptionResponse  =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false) + "  not found");

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }



}
