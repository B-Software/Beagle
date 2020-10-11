package org.bsoftware.beagle.server.handlers;

import org.bsoftware.beagle.server.dto.ErrorDto;
import org.bsoftware.beagle.server.exceptions.UserAlreadyExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RestControllerExceptionHandler
{
    /**
     * Handles NoHandlerFoundException if page not found
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<?> noHandlerFoundExceptionHandler(NoHandlerFoundException noHandlerFoundException)
    {
        return new ResponseEntity<>(new ErrorDto(noHandlerFoundException), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles MethodArgumentNotValidException, then it thrown
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        return new ResponseEntity<>(new ErrorDto(methodArgumentNotValidException), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles BadCredentialsException if login attempt was unsuccessful
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException badCredentialsException)
    {
        return new ResponseEntity<>(new ErrorDto(badCredentialsException), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles UserAlreadyExistsException if user already exists
     */
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsExceptionHandler(UserAlreadyExistsException userAlreadyExistsException)
    {
        return new ResponseEntity<>(new ErrorDto(userAlreadyExistsException), HttpStatus.CONFLICT);
    }

    /**
     * Handles all other servlet exceptions, which were not handled by others handlers
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception exception)
    {
        return new ResponseEntity<>(new ErrorDto(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}