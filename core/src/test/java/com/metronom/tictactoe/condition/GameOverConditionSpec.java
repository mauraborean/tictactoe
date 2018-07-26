package com.metronom.tictactoe.condition;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.GameOverCondition;
import com.metronom.tictactoe.condition.impl.GameOverConditionImpl;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.DummyPlayer;
import com.metronom.tictactoe.player.Player;

@Test
public class GameOverConditionSpec {

    private GameOverCondition condition;

    @BeforeMethod
    public void beforeMethod() {
	condition = new GameOverConditionImpl();
    }

    public void whenBoardIsFullThenGameIsOver() {
	Board dummyBoard = fillGameOverBoard();
	boolean result = condition.isGameOver(dummyBoard);
	assertTrue(result);
    }

    public void whenBoardIsNotFullThenGameIsNotOver() {
	Board dummyBoard = fillGameNotOverBoard();
	boolean result = condition.isGameOver(dummyBoard);
	assertFalse(result);
    }

    private Board fillGameOverBoard() {
	Board dummyBoard = new Board(3, 3, 10);
	Player dummyPlayer1 = new DummyPlayer();
	Player dummyPlayer2 = new DummyPlayer();
	dummyBoard.play(new Move(new Coordinate(0, 0), dummyPlayer1));
	dummyBoard.play(new Move(new Coordinate(0, 1), dummyPlayer2));
	dummyBoard.play(new Move(new Coordinate(0, 2), dummyPlayer1));
	dummyBoard.play(new Move(new Coordinate(1, 0), dummyPlayer2));
	dummyBoard.play(new Move(new Coordinate(1, 1), dummyPlayer1));
	dummyBoard.play(new Move(new Coordinate(1, 2), dummyPlayer2));
	dummyBoard.play(new Move(new Coordinate(2, 0), dummyPlayer1));
	dummyBoard.play(new Move(new Coordinate(2, 1), dummyPlayer2));
	dummyBoard.play(new Move(new Coordinate(2, 2), dummyPlayer1));
	return dummyBoard;
    }

    private Board fillGameNotOverBoard() {
	Board dummyBoard = new Board(4, 3, 10);
	Player dummyPlayer1 = new DummyPlayer();
	Player dummyPlayer2 = new DummyPlayer();
	dummyBoard.play(new Move(new Coordinate(0, 0), dummyPlayer1));
	dummyBoard.play(new Move(new Coordinate(0, 2), dummyPlayer1));
	dummyBoard.play(new Move(new Coordinate(1, 0), dummyPlayer2));
	dummyBoard.play(new Move(new Coordinate(1, 2), dummyPlayer2));
	dummyBoard.play(new Move(new Coordinate(2, 2), dummyPlayer1));
	return dummyBoard;
    }
}
