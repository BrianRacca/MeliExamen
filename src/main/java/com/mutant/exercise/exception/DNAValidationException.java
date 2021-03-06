package com.mutant.exercise.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * Exception for validation purposes
 */
public class DNAValidationException extends DNAException {

    private static final long serialVersionUID = 9138142099883351082L;

    public static final String ERROR_SIZE = "The sequence is not NxN format";
    public static final String ERROR_MATCH = "The sequence is not compatible with ADN sequence [a,c,t,g]";

    public DNAValidationException(String error) {
        super(error, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
