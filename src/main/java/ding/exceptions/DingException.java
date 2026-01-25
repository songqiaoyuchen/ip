package ding.exceptions;

/**
 * Custom exception class for the Ding application.
 * Used to represent application-specific errors.
 */
public class DingException extends Exception {
    /**
     * Constructs a DingException with the specified error message.
     *
     * @param message the error message
     */
    public DingException(String message) { 
        super(message); 
    }
}
