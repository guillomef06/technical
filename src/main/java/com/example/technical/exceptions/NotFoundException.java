package com.example.technical.exceptions;

/* FILE NotFoundException
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
