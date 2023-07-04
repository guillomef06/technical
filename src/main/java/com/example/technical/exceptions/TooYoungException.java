package com.example.technical.exceptions;

/**
 * The type Too young exception.
 */
/* FILE TooYoungException
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public class TooYoungException extends RuntimeException {

    /**
     * Instantiates a new Too young exception.
     *
     * @param message the message
     */
    public TooYoungException(String message) {
        super(message);
    }
}
