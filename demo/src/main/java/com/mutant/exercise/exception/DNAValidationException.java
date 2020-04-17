package com.mutant.exercise.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class DNAValidationException extends DNAException {

    private static final long serialVersionUID = 9138142099883351082L;

    private static final String errorSize = "The sequence is not NxN format";
    public static final String errorMatch = "The sequence is not NxN format";

    public DNAValidationException() {
        super(errorSize, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
