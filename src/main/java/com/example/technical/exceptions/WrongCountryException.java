package com.example.technical.exceptions;

/**
 * The type Wrong country exception.
 */
/* FILE WrongCountryException
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public class WrongCountryException extends RuntimeException {

    /**
     * Instantiates a new Wrong country exception.
     *
     * @param message the message
     */
    public WrongCountryException(String message) {
        super(message);
    }
}
