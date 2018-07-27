package com.metronom.tictactoe.exception;

/**
 * Superclass for all unchecked exception that could be thrown during game
 * execution.
 * 
 * @author Maura Borean
 *
 */
public abstract class PlayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with the exception message.
     * 
     * @param msg a String with the exception description
     */
    PlayException(String msg) {
	super(msg);
    }

}
