package com.example.technical.exceptions;

/* FILE WrongCountryException
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public class WrongCountryException extends RuntimeException {

    public WrongCountryException(String message) {
        super(message);
    }
}
