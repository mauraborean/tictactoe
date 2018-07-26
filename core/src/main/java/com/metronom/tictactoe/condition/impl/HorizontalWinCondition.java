package com.metronom.tictactoe.condition.impl;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.game.Move;

/**
 * Represents the logic to evaluate if a player met the condition to win
 * creating a horizontal line. Is represented by:
 * 
 * <code> ___|___|___</code><br>
 * <code> _x_|_x_|_x_</code><br>
 * <code> ___|___|___</code><br>
 * 
 * @author Maura Borean
 *
 */
public class HorizontalWinCondition extends WinCondition {

    @Override
    public boolean isPlayerWinner(Board board, Move move) {
	boolean isHorizontalWin = true;
	for (int i = 0; i < board.getSize() && isHorizontalWin; ++i) {
	    isHorizontalWin = checkPlayerAtPosition(board, move.getPlayerId(), i, move.getY());
	}
	return isHorizontalWin;
    }
}
