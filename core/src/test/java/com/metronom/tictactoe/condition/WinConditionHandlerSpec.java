package com.metronom.tictactoe.condition;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.condition.impl.WinConditionHandlerImpl;
import com.metronom.tictactoe.game.Move;

@Test
public class WinConditionHandlerSpec {

    private WinConditionHandler winConditionHandler;
    private List<WinCondition> winConditions;

    @BeforeMethod
    public void beforeMethod() {
	winConditions = getWinConditions();
	winConditionHandler = new WinConditionHandlerImpl(winConditions);
    }

    public void whenOneOfTheConditionsIsMetThenResultIsTrue() {
	when(winConditions.get(0).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);
	when(winConditions.get(1).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(true);
	when(winConditions.get(2).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);
	when(winConditions.get(3).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);

	boolean result = winConditionHandler.isWin(any(Board.class), any(Move.class));
	assertTrue(result);
    }

    public void whenNoneOfTheConditionsIsMetThenResultIsFalse() {
	when(winConditions.get(0).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);
	when(winConditions.get(1).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);
	when(winConditions.get(2).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);
	when(winConditions.get(3).isPlayerWinner(any(Board.class), any(Move.class))).thenReturn(false);

	boolean result = winConditionHandler.isWin(any(Board.class), any(Move.class));
	assertFalse(result);
    }

    private List<WinCondition> getWinConditions() {
	List<WinCondition> winConditions = new ArrayList<>();
	for (int i = 0; i < 4; ++i) {
	    winConditions.add(mock(WinCondition.class));
	}
	return winConditions;
    }
}
