package org.bsoftware.beagle.server.handlers;

import org.bsoftware.beagle.server.dto.implementation.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * RestControllerExceptionHandler is standard exception handler for rest api
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@RestControllerAdvice
public class RestControllerExceptionHandler
{
    /**
     * Handles NoHandlerFoundException if page not found
     *
     * @return ResponseEntity with exception message and status code
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<?> noHandlerFoundExceptionHandler(NoHandlerFoundException noHandlerFoundException)
    {
        return new ResponseEntity<>(new ResponseDto(noHandlerFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all other servlet exceptions
     *
     * @return ResponseEntity with exception message and status code
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception exception)
    {
        return new ResponseEntity<>(new ResponseDto(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}