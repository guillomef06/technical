package com.example.technical.exceptions;

/**
 * The type Invalid phone number exception.
 */
/* FILE InvalidPhoneNumberException
AUTHOR Guillaume
PROJECT technical
DATE 02/07/2023 */
public class InvalidPhoneNumberException extends RuntimeException {

    /**
     * Instantiates a new Invalid phone number exception.
     *
     * @param message the message
     */
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
