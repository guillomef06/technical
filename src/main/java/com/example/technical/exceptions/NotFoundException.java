package com.example.technical.exceptions;

/**
 * The type Not found exception.
 */
/* FILE NotFoundException
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public class NotFoundException extends RuntimeException {

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
