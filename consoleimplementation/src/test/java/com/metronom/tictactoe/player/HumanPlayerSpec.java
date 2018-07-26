package com.metronom.tictactoe.player;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.player.impl.HumanPlayer;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.writer.Writer;

@Test
public class HumanPlayerSpec {

    private static final char PLAYER_ID = 'P';
    private Player player;

    private InputHandler inputHandler;
    private Writer writer;

    @BeforeMethod
    public void beforeMethod() {
	inputHandler = mock(InputHandler.class);
	writer = mock(Writer.class);
	player = new HumanPlayer(PLAYER_ID, inputHandler, writer);
    }

    public void whenInstantiatedThenIdIsStored() {
	assertEquals(player.getId(), PLAYER_ID);
    }

    public void whenNextMoveThenMoveIsReturned() {
	Coordinate coordinate = new Coordinate(0, 0);
	when(inputHandler.getPlayerCoodinate(anyChar())).thenReturn(coordinate);
	Coordinate result = player.getPlay();
	assertEquals(result, coordinate);
    }

    public void whenGetNextMoveThenUserInputIsRead() {
	Coordinate coordinate = new Coordinate(0, 0);
	when(inputHandler.getPlayerCoodinate(anyChar())).thenReturn(coordinate);
	player.getPlay();
	verify(inputHandler, times(1)).getPlayerCoodinate(anyChar());
    }

    public void whenRefreshBoardThenWriterIsInvoked() {
	doNothing().when(writer).printBoard(any(Board.class));
	player.refreshBoard(new Board(3, 3, 10));
	verify(writer, times(1)).printBoard(any(Board.class));
    }
}
