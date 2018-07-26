package com.metronom.tictactoe.exception;

/**
 * Unchecked exception that should be thrown when the received board size is not
 * between the required range.
 * 
 * @author Maura Borean
 * @see InitializationException
 */
public class BoardSizeOutOfBoundException extends InitializationException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor with the exception message.
     * 
     * @param msg a String with the exception description
     */
    public BoardSizeOutOfBoundException(String msg) {
	super(msg);
    }

}
