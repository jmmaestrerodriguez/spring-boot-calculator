package com.jmmr.calculator.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@RestControllerAdvice
public class CalculatorExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Pattern ENUM_VALUES = Pattern.compile("the values accepted for Enum class:\\s*\\[\\s*((\\S+\\s*,\\s*)*\\S+)\\s*\\]");

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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        if (exception.getCause() != null && exception.getCause() instanceof InvalidFormatException) {
            Matcher match = ENUM_VALUES.matcher(exception.getCause().getMessage());

            if (match.find()) {
                ExceptionResponse response = new ExceptionResponse(
                        HttpStatus.BAD_REQUEST,
                        exception.getLocalizedMessage(),
                        "value should be: " + match.group(1));
                return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
            }
        }
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                "Bad Request");

        return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "Path '" + request.getDescription(false).substring(4) + "' does not exists.";

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                message);

        return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
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
