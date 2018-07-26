package com.metronom.tictactoe.condition.impl;

import java.util.List;
import java.util.Optional;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.game.Move;

public class WinConditionHandlerImpl implements WinConditionHandler {

    private List<WinCondition> winConditions;

    public WinConditionHandlerImpl(List<WinCondition> winConditions) {
	this.winConditions = winConditions;
    }

    @Override
    public boolean isWin(Board board, Move move) {
	Optional<WinCondition> optWin = winConditions.stream().filter(w -> w.isPlayerWinner(board, move)).findFirst();
	return optWin.isPresent();
    }

}
