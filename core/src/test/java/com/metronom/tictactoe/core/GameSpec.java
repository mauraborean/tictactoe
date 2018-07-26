package com.metronom.tictactoe.core;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InOrder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.GameOverCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.exception.BadInputException;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Game;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.DummyPlayer;
import com.metronom.tictactoe.player.PlayersHandler;
import com.metronom.tictactoe.writer.Writer;

@Test
public class GameSpec {

    private Game game;
    private Move dummyMove;

    private Board board;
    private WinConditionHandler winConditionHandler;
    private GameOverCondition gameOverCondition;
    private PlayersHandler playerHandler;
    private Writer writer;

    @BeforeMethod
    public void beforeMethod() {
	dummyMove = new Move(new Coordinate(0, 1), new DummyPlayer('a'));
	winConditionHandler = mock(WinConditionHandler.class);
	gameOverCondition = mock(GameOverCondition.class);
	playerHandler = mock(PlayersHandler.class);
	writer = mock(Writer.class);
	board = mock(Board.class);
	game = new Game(board, winConditionHandler, gameOverCondition, playerHandler, writer) { 
	    // dummy implementation
	};
    }

    public void whenRunThenOrderExecutionIsCorrect() {
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(false);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(true);
	when(playerHandler.getPlayerMove()).thenReturn(dummyMove);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();

	InOrder inOrder = inOrder(playerHandler, winConditionHandler, gameOverCondition, writer);
	inOrder.verify(playerHandler).refreshPlayerBoard(any(Board.class));
	inOrder.verify(playerHandler).getPlayerMove();
	inOrder.verify(winConditionHandler).isWin(any(Board.class), any(Move.class));
	inOrder.verify(gameOverCondition).isGameOver(any(Board.class));
	inOrder.verify(playerHandler).nextTurn();
	inOrder.verify(playerHandler).refreshPlayerBoard(any(Board.class));
	inOrder.verify(writer).writeInfoMessage(anyString());
    }

    public void whenPlayThenWinConditionIsEvaluated() {
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(true);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(false);
	when(playerHandler.getPlayerMove()).thenReturn(dummyMove);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();
	verify(winConditionHandler, times(1)).isWin(any(Board.class), any(Move.class));
    }

    public void whenPlayAndThereIsNoWinThenGameOverConditionIsEvaluated() {
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(false);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(true);
	when(playerHandler.getPlayerMove()).thenReturn(dummyMove);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();
	verify(gameOverCondition, times(1)).isGameOver(any(Board.class));
    }

    public void whenPlayAndThereIsNoWinAndGameIsNotOverThenContinuePlaying() {
	Move dummyMove2 = new Move(new Coordinate(1, 0), new DummyPlayer());
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(false, true);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(false);
	when(playerHandler.getPlayerMove()).thenReturn(dummyMove, dummyMove2);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();
	verify(winConditionHandler, atLeastOnce()).isWin(any(Board.class), any(Move.class));
	verify(gameOverCondition, times(1)).isGameOver(any(Board.class));
    }

    public void whenWinThenWriterSendsInfoMsgToUser() {
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(true);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(false);
	when(playerHandler.getPlayerMove()).thenReturn(dummyMove);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();
	verify(writer, times(1)).writeInfoMessage(anyString());
    }

    public void whenGameOverThenWriterSendsInfoMsgToUser() {
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(false);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(true);
	when(playerHandler.getPlayerMove()).thenReturn(dummyMove);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();
	verify(writer, times(1)).writeInfoMessage(anyString());
    }

    @SuppressWarnings("unchecked")
    public void whenErrorIsOccurredThenErrorMessageIsSentToUser() {
	when(winConditionHandler.isWin(any(Board.class), any(Move.class))).thenReturn(true);
	when(gameOverCondition.isGameOver(any(Board.class))).thenReturn(true);
	when(playerHandler.getPlayerMove()).thenThrow(BadInputException.class).thenReturn(dummyMove);
	doNothing().when(playerHandler).refreshPlayerBoard(any(Board.class));
	doNothing().when(playerHandler).nextTurn();
	doNothing().when(writer).writeInfoMessage(anyString());
	game.run();
	verify(writer, times(1)).writeErrorMessage(anyString());
    }
}
