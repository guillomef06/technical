package com.example.technical.exceptions;

/**
 * The type Too young exception.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Instantiates a new Too young exception.
     *
     * @param message the message
     */
    public BadRequestException(String message) {
        super(message);
    }
}
