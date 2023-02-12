package com.mghostl.musalatest.handlers;

import com.mghostl.musalatest.exceptions.DroneAlreadyExistsException;
import com.mghostl.musalatest.exceptions.DroneNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RepositoryExceptionHandler {
    @ExceptionHandler(DroneAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsExceptions(DroneAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DroneNotExistsException.class)
    public ResponseEntity<String> handleDoesNotExistsException(DroneNotExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
