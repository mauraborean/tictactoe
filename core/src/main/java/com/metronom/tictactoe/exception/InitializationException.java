package com.metronom.tictactoe.exception;

/**
 * Unchecked exception that should be thrown when the game cannot create an
 * Object.
 * 
 * @author Maura Borean
 * @see PlayException
 */
public class InitializationException extends PlayException {

    private static final long serialVersionUID = 1L;
    private static final String INITIALIZATION_MSG = "Could not start game. An error has occurred: %s";

    /**
     * Constructor with the exception message.
     * 
     * @param msg a String with the exception description
     */
    public InitializationException(String msg) {
	super(String.format(INITIALIZATION_MSG, msg));
    }

}
