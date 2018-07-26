package com.metronom.tictactoe.player;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;

/**
 * Provides the base structure that should be implemented by all the players of
 * the game. Includes the necessary methods for the game to interact with the
 * players.
 * 
 * @author Maura Borean
 *
 */
public interface Player {

    /**
     * Returns the id of the player.
     * 
     * @return char value
     */
    char getId();

    /**
     * Looks for the next player's move, validates it and returns the values in a
     * Coordinate object.
     * 
     * @return {@link Coordinate}
     */
    Coordinate getPlay();

    /**
     * Updates the board for the player.
     * 
     * @param board {@link Board}
     */
    void refreshBoard(Board board);

}
