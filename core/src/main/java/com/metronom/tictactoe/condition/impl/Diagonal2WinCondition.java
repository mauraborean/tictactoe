package com.metronom.tictactoe.condition.impl;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.game.Move;

/**
 * Represents the logic to evaluate if a player met the condition to win
 * creating a diagonal line that is defined like:
 * 
 * <code> ___|___|_x_</code><br>
 * <code> ___|_x_|___</code><br>
 * <code> _x_|___|___</code><br>
 * 
 * @author Maura Borean
 *
 */
public class Diagonal2WinCondition extends WinCondition {

    @Override
    public boolean isPlayerWinner(Board board, Move move) {
	boolean isDiagonalWin = false;
	if (isDiagonalWinPossible(board, move)) {
	    isDiagonalWin = checkDiagonalWin(board, move);
	}
	return isDiagonalWin;
    }

    private boolean isDiagonalWinPossible(Board board, Move move) {
	return move.getX() + move.getY() == (board.getSize() - 1);
    }

    private boolean checkDiagonalWin(Board board, Move move) {
	boolean isDiagonalWin = true;
	for (int i = 0, j = board.getSize() - 1; i < board.getSize() && isDiagonalWin; ++i, --j) {
	    isDiagonalWin = checkPlayerAtPosition(board, move.getPlayerId(), i, j);
	}
	return isDiagonalWin;
    }
}
