package com.metronom.tictactoe.condition;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Move;

/**
 * Encapsulates the logic to determine whether the condition to win the game is
 * met.
 * 
 * @author Maura Borean
 *
 */
public abstract class WinCondition {

    /**
     * Given a board, verifies if certain player has made a move in the given
     * position.
     * 
     * @param board {@link Board} to look for the plays
     * @param movePlayerId char value with the player's id that performed the move
     * @param x int value with the X point to check for
     * @param y int value with the Y point to check for
     * @return {@code true} if actual player id and player at given position are the
     *         same, {@code false} otherwise
     */
    protected boolean checkPlayerAtPosition(Board board, char movePlayerId, int x, int y) {
	boolean isWin = true;
	Character playerId = board.getPlayerIdAtPosition(x, y);
	if (playerId == null || playerId != movePlayerId) {
	    isWin = false;
	}
	return isWin;
    }

    /**
     * Evaluates whether {@link Move#getPlayerId()} meets the condition to win the
     * game.
     * 
     * @param board {@link Board} to evaluate
     * @param move {@link Move}
     * @return {@code true} if player is winner, {@code false} otherwise
     */
    public abstract boolean isPlayerWinner(Board board, Move move);

}
