package com.metronom.tictactoe.reader;

import com.metronom.tictactoe.game.Coordinate;

/**
 * Provides the structure for the class to manage players inputs.<br>
 * <br>
 * The class implementing {@code InputHandler} is in charge of reading and
 * validating the player's input.
 * 
 * @author Maura Borean
 *
 */
public interface InputHandler {

    /**
     * Reads a player's next move, checks whether it is valid and returns it as a
     * Coordinate instance.
     * 
     * @param playerId a char with the player identifier
     * @return {@link Coordinate}
     */
    Coordinate getPlayerCoodinate(char playerId);

}
