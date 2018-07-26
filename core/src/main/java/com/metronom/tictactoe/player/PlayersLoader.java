package com.metronom.tictactoe.player;

/**
 * Loads and creates the players for the game.
 * 
 * @author Maura Borean
 *
 */
public interface PlayersLoader {

    /**
     * Creates an array of players for the game.
     * 
     * @return {@link Player} array
     */
    Player[] loadPlayers();
}
