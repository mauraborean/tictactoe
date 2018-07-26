package com.metronom.tictactoe.condition;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.condition.impl.Diagonal1WinCondition;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.DummyPlayer;
import com.metronom.tictactoe.player.Player;

@Test
public class Diagonal1WinConditionSpec {

    private WinCondition winCondition;
    private Player dummyPlayer;

    @BeforeMethod
    public void beforeMethod() {
	winCondition = new Diagonal1WinCondition();
	dummyPlayer = new DummyPlayer();
    }

    public void whenPlayerWinsInDiagonal1LineThenResultIsTrue() {
	Board board = fillDiagonal1WinningBoard();
	Coordinate coordinate = new Coordinate(3, 3);
	Move lastMove = new Move(coordinate, dummyPlayer);
	boolean result = winCondition.isPlayerWinner(board, lastMove);
	assertTrue(result);
    }

    public void whenPlayerDoesNotWinInDiagonal1LineAndLastMoveWasADiagonalCoordinateThenResultIsFalse() {
	Board board = fillNoWinningBoard();
	Coordinate coordinate = new Coordinate(2, 2);
	Move lastMove = new Move(coordinate, dummyPlayer);
	boolean result = winCondition.isPlayerWinner(board, lastMove);
	assertFalse(result);
    }

    public void whenPlayerDoesNotWinInDiagonal1LineAndLastMoveWasNotADiagonalCoordinateThenResultIsFalse() {
	Board board = fillNoWinningBoard();
	Coordinate coordinate = new Coordinate(0, 2);
	Move lastMove = new Move(coordinate, dummyPlayer);
	boolean result = winCondition.isPlayerWinner(board, lastMove);
	assertFalse(result);
    }

    private Board fillDiagonal1WinningBoard() {
	Board dummyBoard = new Board(4, 3, 10);
	dummyBoard.play(new Move(new Coordinate(0, 0), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(1, 1), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(2, 2), dummyPlayer));
	dummyBoard.play(new Move(new Coordinate(3, 3), dummyPlayer));
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
