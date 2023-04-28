package com.jmmr.calculator.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class CalculatorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> globalErrors = exception.getBindingResult().getGlobalErrors().stream()
                .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> errors = new ArrayList<>();
        errors.addAll(fieldErrors);
        errors.addAll(globalErrors);

        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(), errors);

        return handleExceptionInternal(exception, response, headers, response.getStatus(), request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getLocalizedMessage(),
                "An unknown error has occurred");
        return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
    }




}
