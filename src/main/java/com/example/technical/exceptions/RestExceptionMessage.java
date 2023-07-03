package com.example.technical.exceptions;

import java.io.Serializable;
import java.time.ZonedDateTime;

/* FILE RestExceptionMessage
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

public record RestExceptionMessage(String message, ZonedDateTime zonedDateTime) implements Serializable {
}
