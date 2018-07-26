package com.metronom.tictactoe.condition.impl;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.GameOverCondition;

public class GameOverConditionImpl implements GameOverCondition {

    @Override
    public boolean isGameOver(Board board) {
	boolean isGameOver = true;

	for (int y = 0; y < board.getSize() && isGameOver; ++y) {
	    for (int x = 0; x < board.getSize(); ++x) {
		if (board.getPlayerIdAtPosition(x, y) == null) {
		    isGameOver = false;
		    break;
		}
	    }
	}

	return isGameOver;
    }
}
