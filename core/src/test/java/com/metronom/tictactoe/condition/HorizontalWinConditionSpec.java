package com.metronom.tictactoe.condition;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.condition.impl.HorizontalWinCondition;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.DummyPlayer;
import com.metronom.tictactoe.player.Player;

@Test
public class HorizontalWinConditionSpec {

    private WinCondition winCondition;
    private Player dummyPlayer;

    @BeforeMethod
    public void beforeMethod() {
	winCondition = new HorizontalWinCondition();
	dummyPlayer = new DummyPlayer();
    }

    public void whenPlayerWinsInHorizontalLineThenResultIsTrue() {
	Board board = fillHorizontallyWinningBoard();
	Coordinate coordinate = new Coordinate(4, 0);
	Move lastMove = new Move(coordinate, dummyPlayer);
	boolean result = winCondition.isPlayerWinner(board, lastMove);
	assertTrue(result);
    }

    public void whenPlayerDoesNotWinInHorizontalLineThenResultIsFalse() {
	Board board = fillNoWinningBoard();
	Coordinate coordinate = new Coordinate(0, 2);
	Move lastMove = new Move(coordinate, dummyPlayer);
	boolean result = winCondition.isPlayerWinner(board, lastMove);
	assertFalse(result);
    }

    private Board fillHorizontallyWinningBoard() {
	Board dummyBoard = new Board(5, 3, 10);
	dummyBoard.play(new Move(new Coordinate(0, 0), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(1, 0), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(2, 0), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(3, 0), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(4, 0), dummyPlayer));
	return dummyBoard;
    }

    private Board fillNoWinningBoard() {
	Board dummyBoard = new Board(3, 3, 10);
	dummyBoard.play(new Move(new Coordinate(2, 1), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(1, 1), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(0, 2), dummyPlayer));
	return dummyBoard;
    }
}
