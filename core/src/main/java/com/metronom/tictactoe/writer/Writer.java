package com.metronom.tictactoe.writer;

import com.metronom.tictactoe.board.Board;

/**
 * Provides the structure for showing messages to the users<br>
 * <br>
 * The class implementing {@code Writer} is in charge of sending information and
 * error messages to the players, and printing the board.
 * 
 * @author Maura Borean
 *
 */
public interface Writer {

    /**
     * Writes an information message to the player.
     * 
     * @param msg a String with the message
     */
    void writeInfoMessage(String msg);

    /**
     * Writes an error message to the player.
     * 
     * @param msg a String with the message
     */
    void writeErrorMessage(String msg);

    /**
     * Writes a graphic representation of the board to the player.
     * 
     * @param board {@link Board} to print
     */
    void printBoard(Board board);

}
