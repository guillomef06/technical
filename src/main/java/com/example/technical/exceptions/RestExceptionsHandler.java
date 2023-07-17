package com.example.technical.exceptions;

import com.example.technical.config.AppPropertiesResolver;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * This is the RestController advice that will globally handle
 * our exceptions and send a normalized RestExceptionMessage output
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionsHandler {

    /**
     * Configuration file
     */
    private final AppPropertiesResolver appPropertiesResolver;

    /**
     * Handle generic exception
     *
     * @param e the exception
     * @return the rest exception message
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionMessage handleException(Exception e) {
        if (log.isErrorEnabled()) {
                log.error("Unexpected error has occurred: {}", e.getMessage());
        }
        return new RestExceptionMessage(e.getMessage(), ZonedDateTime.now(ZoneId.of(appPropertiesResolver.getZoneId())));
    }

    /**
     * Handle method argument not valid exception
     *
     * @param e the exception
     * @return the rest exception message
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        if (log.isWarnEnabled()) {
            log.warn("MethodArgumentNotValidException: {}", errors);
        }
        return new RestExceptionMessage(errors.toString(), ZonedDateTime.now(ZoneId.of(appPropertiesResolver.getZoneId())));
    }

    /**
     * Handle constraint violation exception
     *
     * @param e the exception
     * @return the rest exception message
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionMessage handleConstraintViolationException(ConstraintViolationException e) {
        return new RestExceptionMessage(e.getMessage(), ZonedDateTime.now(ZoneId.of(appPropertiesResolver.getZoneId())));
    }

    /**
     * Handle too young exception
     *
     * @param e the exception
     * @return the rest exception message
     */
    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionMessage handleBadRequestException(BadRequestException e) {
        return new RestExceptionMessage(e.getMessage(), ZonedDateTime.now(ZoneId.of(appPropertiesResolver.getZoneId())));
    }

    /**
     * Handle not found exception
     *
     * @param e the exception
     * @return the rest exception message
     */
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestExceptionMessage handleNotFoundException(NotFoundException e) {
        return new RestExceptionMessage(e.getMessage(), ZonedDateTime.now(ZoneId.of(appPropertiesResolver.getZoneId())));
    }
}
