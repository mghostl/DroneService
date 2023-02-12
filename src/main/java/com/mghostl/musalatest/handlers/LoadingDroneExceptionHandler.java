package com.mghostl.musalatest.handlers;

import com.mghostl.musalatest.exceptions.DroneIsNotReadyForLoadingException;
import com.mghostl.musalatest.exceptions.OverloadDroneException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoadingDroneExceptionHandler {
    @ExceptionHandler({OverloadDroneException.class, DroneIsNotReadyForLoadingException.class})
    public ResponseEntity<String> handleLoadingDroneException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
