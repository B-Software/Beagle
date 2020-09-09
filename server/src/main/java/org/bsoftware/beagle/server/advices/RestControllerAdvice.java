package org.bsoftware.beagle.server.advices;

import org.bsoftware.beagle.server.dto.implementation.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;

/**
 * RestControllerAdvice provides error handling for rest api
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@org.springframework.web.bind.annotation.RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAdvice extends ResponseEntityExceptionHandler
{
    /**
     * Autowired HttpHeaders object
     * Used as a bean, which already provides Json headers
     */
    private final HttpHeaders httpHeaders;

    /**
     * Handles ConstraintViolationException if it was thrown to controller
     *
     * @param exception ConstraintViolationException exception
     * @return ResponseEntity to servlet
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception, WebRequest webRequest)
    {
        return handleExceptionInternal(exception, new ResponseDto(exception.getMessage()), httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY, webRequest);
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param httpHeaders autowired HttpHeaders object
     */
    @Autowired
    public RestControllerAdvice(HttpHeaders httpHeaders)
    {
        this.httpHeaders = httpHeaders;
    }
}