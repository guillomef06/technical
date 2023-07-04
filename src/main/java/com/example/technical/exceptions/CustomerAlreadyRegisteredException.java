package com.example.technical.exceptions;

/**
 * The type Customer already registered exception.
 */
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
