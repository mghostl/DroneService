package com.mghostl.musalatest.exceptions;

public class DroneAlreadyExistsException extends RuntimeException{
    public DroneAlreadyExistsException(String msg) {
        super(msg);
    }
}
