package com.metronom.tictactoe.board;

import static org.testng.Assert.*;

import static org.mockito.Mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.exception.BadInputException;
import com.metronom.tictactoe.exception.BoardSizeOutOfBoundException;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.Player;

@Test
public class BoardSpec {

    private static final int BOARD_SIZE = 3;
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 10;

    private Board board;
    private Player dummyPlayer;

    @BeforeMethod
    public void beforeMethod() {
	board = new Board(BOARD_SIZE, MIN_BOARD_SIZE, MAX_BOARD_SIZE);
	dummyPlayer = mock(Player.class);
    }

    public void whenInstantiatedBoundariesAreSet() {
	assertEquals(board.getSize(), BOARD_SIZE);
    }

    @Test(expectedExceptions = { BoardSizeOutOfBoundException.class })
    public void whenBoundariesAreSmallerThanMinSizeThenBoardSizeOutOfBoundException() {
	board = new Board(2, MIN_BOARD_SIZE, MAX_BOARD_SIZE);
    }

    @Test(expectedExceptions = { BoardSizeOutOfBoundException.class })
    public void whenBoundariesAreHigherThanMaxSizeThenBoardSizeOutOfBoundException() {
	board = new Board(11, MIN_BOARD_SIZE, MAX_BOARD_SIZE);
    }

    @Test(expectedExceptions = { BadInputException.class })
    public void whenXisOutsideBoardThenBadInputException() {
	Coordinate coordinate = new Coordinate(4, 2);
	Move move = new Move(coordinate, dummyPlayer);
	board.play(move);
    }

    @Test(expectedExceptions = { BadInputException.class })
    public void whenXisNegativeThenBadInputException() {
	Coordinate coordinate = new Coordinate(-2, 2);
	Move move = new Move(coordinate, dummyPlayer);
	board.play(move);
    }

    @Test(expectedExceptions = { BadInputException.class })
    public void whenYisOutsideBoardThenBadInputException() {
	Coordinate coordinate = new Coordinate(2, 4);
	Move move = new Move(coordinate, dummyPlayer);
	board.play(move);
    }

    @Test(expectedExceptions = { BadInputException.class })
    public void whenYisNegativeThenBadInputException() {
	Coordinate coordinate = new Coordinate(2, -2);
	Move move = new Move(coordinate, dummyPlayer);
	board.play(move);
    }

    @Test(expectedExceptions = { BadInputException.class })
    public void whenMoveIsAlreadyPlayedThenBadInputException() {
	Coordinate coordinate = new Coordinate(2, 2);
	Move move = new Move(coordinate, dummyPlayer);
	board.play(move);
	board.play(move);
    }

    public void whenValuesAreOkThenPlay() {
	Coordinate coordinate = new Coordinate(1, 2);
	Move move = new Move(coordinate, dummyPlayer);
	board.play(move);
	Character c = board.getPlayerIdAtPosition(move.getX(), move.getY());
	assertEquals(c.charValue(), dummyPlayer.getId());
    }

    public void whenGetCopyThenIsNotSameBoardInstance() {
	Board copy = board.getCopy();
	assertNotEquals(copy, board);
    }

    public void whenGetCopyThenBoardValuesAreCopied() {
	Move move = new Move(new Coordinate(1, 2), dummyPlayer);
	board.play(move);
	Board copy = board.getCopy();
	assertEquals(copy.getPlayerIdAtPosition(1, 2).charValue(), dummyPlayer.getId());
    }

}
