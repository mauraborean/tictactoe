package com.metronom.tictactoe.player;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Move;

/**
 * Manages the player interaction with the core of the game.
 * 
 * @author Maura Borean
 *
 */
public interface PlayersHandler {

    /**
     * Returns the array of players.
     * 
     * @return {@link Player} array
     */
    Player[] getPlayers();

    /**
     * Returns the player associated with the received id.
     * 
     * @param id a char
     * @return the {@link Player} or {@code null} if the id does not exist in the
     *         list of players
     * 
     */
    Player getPlayerById(char id);

    /**
     * Returns the next player move.
     * 
     * @return {@link Move}
     */
    Move getPlayerMove();

    /**
     * Updates the board reference for the player(s).
     * 
     * @param board {@link Board}
     */
    void refreshPlayerBoard(Board board);

    /**
     * Calculates the next player to play.
     */
    void nextTurn();

}
