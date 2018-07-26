package com.metronom.tictactoe.reader;

import com.metronom.tictactoe.exception.InitializationException;

/**
 * Provides the structure for the game to look for necessary values for
 * configuration and instantiation.<br>
 * <br>
 * The class implementing {@code ConfigurationReader} contains the necessary
 * information to create the {@link Player}s and the {@link Board}s.
 * 
 * @author Maura Borean
 *
 */
public interface ConfigurationReader {

    /**
     * Looks and returns the id for the given player key.
     * 
     * @param key a String with the player key
     * @return char value with player id
     * @throws InitializationException if id was not found
     */
    char getPlayerId(String key) throws InitializationException;

    /**
     * Looks and returns the game's board size.
     * 
     * @return an int with the board size
     * @throws InitializationException if a valid board size is not found
     */
    int getBoardSize() throws InitializationException;

}
