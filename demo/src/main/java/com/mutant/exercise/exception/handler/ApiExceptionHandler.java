package com.mutant.exercise.exception.handler;

import com.mutant.exercise.exception.DNAException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DNAException.class)
    private ResponseEntity<Object> handleApiRequestException(DNAException e) {
        return new ResponseEntity<>(e, BAD_REQUEST);
    }
}
