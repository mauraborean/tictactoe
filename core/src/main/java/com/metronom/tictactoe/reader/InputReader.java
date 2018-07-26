package com.metronom.tictactoe.reader;

/**
 * Provides the structure for a class to read player's inputs.<br>
 * <br>
 * The class implementing {@code InputReader} is in charge of catching the
 * player's input.
 * 
 * @author Maura Borean
 *
 */
public interface InputReader {

    /**
     * Reads and returns the player's next move.
     * 
     * @param playerId a char with the player's identifier
     * @return the player's input
     */
    String getInput(char playerId);

}
