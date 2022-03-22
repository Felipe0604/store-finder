package com.jumbo.store.finder.controller.handler;

import com.jumbo.store.finder.exception.EmptyDataBaseException;
import com.jumbo.store.finder.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller handler exception.
 *
 * @author Felipe Gonzalez
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Exception Handler for NoDataFoundException.
     *
     * @param exception is NoDataFoundException.
     * @return ResponseEntity.
     */
    @ExceptionHandler(NoDataFoundException.class)
    protected ResponseEntity handleNoDataFoundException(NoDataFoundException exception){
        log.info("Empty result exception, message: {}, stacktrace: {}",
                exception.getMessage(), exception.getStackTrace());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Exception Handler for EmptyDataBaseException.
     *
     * @param exception is EmptyDataBaseException.
     * @return ResponseEntity.
     */
    @ExceptionHandler(EmptyDataBaseException.class)
    protected ResponseEntity handleEmptyDataBaseException(EmptyDataBaseException exception){
        log.info("No content in the source exception, message: {}, stacktrace: {}",
                exception.getMessage(), exception.getStackTrace());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
