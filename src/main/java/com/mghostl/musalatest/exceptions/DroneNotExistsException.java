package com.mghostl.musalatest.exceptions;

public class DroneNotExistsException extends RuntimeException{
    public DroneNotExistsException(String msg) {
        super(msg);
    }
}
