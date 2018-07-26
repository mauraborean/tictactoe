package com.metronom.tictactoe.exception;

/**
 * Unchecked exception that should be thrown when an input that does not meet the
 * requirements for the game is found.
 * 
 * @author Maura Borean
 * @see PlayException
 */
public class BadInputException extends PlayException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with the exception message.
     * 
     * @param msg a String with the exception description
     */
    public BadInputException(String msg) {
	super(msg);
    }
}
