package com.oracle.bowling.exception;

/**
 * This class represents custom exception for the application.
 */
public class InvalidPinsException extends RuntimeException {

    /**
     * @param message, invalid input message.
     */
    public InvalidPinsException(String message) {
        super(message);
    }
}
