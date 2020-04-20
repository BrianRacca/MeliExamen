package com.mutant.exercise.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


/**
 * Core custom Exception
 */
public class DNAException extends RuntimeException {


    private static final long serialVersionUID = -5766485759623492409L;
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public DNAException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

}
