package com.example.technical.exceptions;

import java.io.Serializable;
import java.time.ZonedDateTime;

/* FILE RestExceptionMessage
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

/**
 * This record will be sent when exceptions are thrown to normalize output
 * @param message will describe the error
 * @param zonedDateTime to replace the timestamp
 */
public record RestExceptionMessage(String message, ZonedDateTime zonedDateTime) implements Serializable {
}
