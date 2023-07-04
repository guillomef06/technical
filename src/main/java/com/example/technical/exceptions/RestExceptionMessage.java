package com.example.technical.exceptions;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * This record will be sent when exceptions are thrown to normalize output
 *
 * @param message       will describe the error
 * @param zonedDateTime to replace the timestamp
 */
public record RestExceptionMessage(String message, ZonedDateTime zonedDateTime) implements Serializable {
}
