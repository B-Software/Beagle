package org.bsoftware.beagle.server.handlers;

import org.bsoftware.beagle.server.dto.ErrorDto;
import org.bsoftware.beagle.server.exceptions.InsufficientCheckAmountException;
import org.bsoftware.beagle.server.exceptions.KeyDoesNotExistsOrAlreadyActivatedException;
import org.bsoftware.beagle.server.exceptions.UserAlreadyExistsException;
import org.bsoftware.beagle.server.exceptions.WrongFileExtensionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
     * Handles BadCredentialsException, then it thrown
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException badCredentialsException)
    {
        return new ResponseEntity<>(new ErrorDto(badCredentialsException), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles NoHandlerFoundException, then it thrown
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<?> noHandlerFoundExceptionHandler(NoHandlerFoundException noHandlerFoundException)
    {
        return new ResponseEntity<>(new ErrorDto(noHandlerFoundException), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles NoHandlerFoundException, then it thrown
     */
    @ExceptionHandler(value = InsufficientCheckAmountException.class)
    public ResponseEntity<?> insufficientCheckAmountExceptionHandler(InsufficientCheckAmountException insufficientCheckAmountException)
    {
        return new ResponseEntity<>(new ErrorDto(insufficientCheckAmountException), HttpStatus.LOCKED);
    }

    /**
     * Handles exceptions with UNPROCESSABLE_ENTITY status, then they thrown
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, WrongFileExtensionException.class})
    public ResponseEntity<?> unprocessableEntityExceptionHandler(Exception unprocessableEntityException)
    {
        return new ResponseEntity<>(new ErrorDto(unprocessableEntityException), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles exceptions with CONFLICT status, then they thrown
     */
    @ExceptionHandler(value = {UserAlreadyExistsException.class, KeyDoesNotExistsOrAlreadyActivatedException.class})
    public ResponseEntity<?> conflictExceptionHandler(Exception conflictException)
    {
        return new ResponseEntity<>(new ErrorDto(conflictException), HttpStatus.CONFLICT);
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