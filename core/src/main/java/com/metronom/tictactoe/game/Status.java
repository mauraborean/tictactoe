package com.metronom.tictactoe.game;

/**
 * Contains the different states of the game.
 * 
 * @author Maura Borean
 *
 */
public enum Status {

    /**
     * Game has not finished.
     */
    PLAYING(""),
    /**
     * A player has win the game.
     */
    WON("Player %c is the winner!"),
    /**
     * No more moves are available and there is no winner.
     */
    GAME_OVER("Game over! No winner this time!");

    private String msg;

    private Status(String msg) {
	this.msg = msg;
    }

    /**
     * Returns the message to show to the player.
     * 
     * @return a String value
     */
    public String getMsg() {
	return msg;
    }

    /**
     * Evaluates whether the game has finished or can be continued.
     * 
     * @return {@code true} if {@link Status#PLAYING} is the current status,
     *         {@code false} otherwise
     */
    public boolean continuePlaying() {
	return this == Status.PLAYING;
    }
}
