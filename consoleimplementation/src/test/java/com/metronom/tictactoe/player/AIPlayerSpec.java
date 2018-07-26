package com.metronom.tictactoe.player;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.player.impl.AIPlayer;
import com.metronom.tictactoe.writer.Writer;

@Test
public class AIPlayerSpec {

    private Player player;
    private Writer writer;
    private Board board;

    @BeforeMethod
    public void beforeMethod() {
	writer = mock(Writer.class);
	player = new AIPlayer('a', writer);
    }

    public void whenGetNextPlayerMoveThenAvailableMoveIsReturned() {
	List<Coordinate> availablePlays = getAvailablePlays();
	board = spy(getBoardWithAvailableMoves());
	player.refreshBoard(board);
	Coordinate result = player.getPlay();
	assertTrue(availablePlays.contains(result));
    }

    public void whenNoMoreMovesAvailableThenXValueIsNotSet() {
	board = spy(getBoardWithNoAvailableMoves());
	player.refreshBoard(board);
	Coordinate result = player.getPlay();
	assertEquals(result.getX(), -1);
    }

    private Board getBoardWithAvailableMoves() {
	Board board = new Board(3, 3, 10);
	board.play(new Move(new Coordinate(0, 0), player));
	board.play(new Move(new Coordinate(1, 2), player));
	board.play(new Move(new Coordinate(2, 0), player));
	board.play(new Move(new Coordinate(2, 1), player));
	board.play(new Move(new Coordinate(2, 2), player));
	return board;
    }

    private List<Coordinate> getAvailablePlays() {
	List<Coordinate> availableCoordinates = new ArrayList<>();
	availableCoordinates.add(new Coordinate(1, 1));
	availableCoordinates.add(new Coordinate(0, 1));
	availableCoordinates.add(new Coordinate(1, 0));
	availableCoordinates.add(new Coordinate(0, 2));
	return availableCoordinates;
    }

    private Board getBoardWithNoAvailableMoves() {
	Board board = new Board(3, 3, 10);
	board.play(new Move(new Coordinate(0, 0), player));
	board.play(new Move(new Coordinate(1, 2), player));
	board.play(new Move(new Coordinate(2, 0), player));
	board.play(new Move(new Coordinate(2, 1), player));
	board.play(new Move(new Coordinate(2, 2), player));
	board.play(new Move(new Coordinate(1, 1), player));
	board.play(new Move(new Coordinate(0, 1), player));
	board.play(new Move(new Coordinate(1, 0), player));
	board.play(new Move(new Coordinate(0, 2), player));
	return board;
    }

}
