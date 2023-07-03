package com.example.technical.exceptions;

/* FILE InvalidPhoneNumberException
AUTHOR Guillaume
PROJECT technical
DATE 02/07/2023 */
public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
