package com.example.technical.exceptions;

/* FILE CustomerAlreadyRegisteredException
AUTHOR Guillaume
PROJECT technical
DATE 02/07/2023 */
public class CustomerAlreadyRegisteredException extends RuntimeException {

    public CustomerAlreadyRegisteredException(String message) {
        super(message);
    }
}
