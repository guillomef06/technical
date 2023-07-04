package com.example.technical.exceptions;

/**
 * The type Customer already registered exception.
 */
/* FILE CustomerAlreadyRegisteredException
AUTHOR Guillaume
PROJECT technical
DATE 02/07/2023 */
public class CustomerAlreadyRegisteredException extends RuntimeException {

    /**
     * Instantiates a new Customer already registered exception.
     *
     * @param message the message
     */
    public CustomerAlreadyRegisteredException(String message) {
        super(message);
    }
}
