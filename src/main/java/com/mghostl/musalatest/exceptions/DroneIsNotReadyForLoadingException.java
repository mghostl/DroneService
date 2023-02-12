package com.mghostl.musalatest.exceptions;

public class DroneIsNotReadyForLoadingException extends RuntimeException{
    public DroneIsNotReadyForLoadingException(String msg) {
        super(msg);
    }
}
