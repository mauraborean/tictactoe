package com.metronom.tictactoe.condition.impl;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.game.Move;

/**
 * Represents the logic to evaluate if a player met the condition to win
 * creating a vertical line.Is represented by:
 * 
 * <code> ___|_x_|___</code><br>
 * <code> ___|_x_|___</code><br>
 * <code> ___|_x_|___</code><br>
 * 
 * @author Maura Borean
 *
 */
public class VerticalWinCondition extends WinCondition {

    @Override
    public boolean isPlayerWinner(Board board, Move move) {
	boolean isVerticalWin = true;
	for (int i = 0; i < board.getSize() && isVerticalWin; ++i) {
	    isVerticalWin = checkPlayerAtPosition(board, move.getPlayerId(), move.getX(), i);
	}
	return isVerticalWin;
    }
}
