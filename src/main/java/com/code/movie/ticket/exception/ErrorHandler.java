package com.code.movie.ticket.exception;

import com.code.movie.ticket.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintValidationException(
            ConstraintViolationException ex) {
        var message = ex.getMessage().split(":")[1];
        return handleException(ex, message, BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        var message = ex.getMessage();
        return handleException(ex, message, BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        var message = String.format("Request method %s is not supported.", ex.getMessage());
        return handleException(ex, message, METHOD_NOT_ALLOWED);
    }

    private ResponseEntity<ErrorResponse> buildReponse(HttpStatusCode status, ErrorResponse body) {
        return ResponseEntity
                .status(status)
                .headers(HttpHeaders.EMPTY)
                .body(body);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception exception, String message, HttpStatusCode status) {
        logException(exception);
        return buildReponse(status, buildResponseBody(message));
    }

    private ErrorResponse buildResponseBody(String message) {
        return ErrorResponse.create(message);
    }

    private void logException(Exception exception) {
        LOGGER.error("Exception occurred during request processing {}", exception.getMessage());
    }


}
