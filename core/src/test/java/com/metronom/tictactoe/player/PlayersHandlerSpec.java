package com.metronom.tictactoe.player;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.impl.PlayersHandlerImpl;
import com.metronom.tictactoe.writer.Writer;

@Test
public class PlayersHandlerSpec {

    private PlayersHandler playerHandler;
    private Writer writer;
    private PlayersLoader playerLoader;
    private Player[] dummyPlayers;
    private Coordinate dummyCoordinate;
    private Board dummyBoard;

    @BeforeMethod
    public void beforeMethod() {
	writer = mock(Writer.class);
	playerLoader = mock(PlayersLoader.class);
	dummyPlayers = getDummyPlayers();
	dummyCoordinate = new Coordinate(0, 0);
	dummyBoard = mock(Board.class);
	doReturn(dummyPlayers).when(playerLoader).loadPlayers();

	playerHandler = new PlayersHandlerImpl(playerLoader, writer);
    }

    public void whenInstantiatedPlayersAreLoaded() {
	assertEquals(playerHandler.getPlayers().length, dummyPlayers.length);
    }

    public void whenActualPlayerIsP1ThenNextPlayerIsP2() {
	when(dummyPlayers[1].getPlay()).thenReturn(dummyCoordinate);
	playerHandler.nextTurn();
	Move move = playerHandler.getPlayerMove();
	assertEquals(move.getPlayerId(), dummyPlayers[1].getId());
    }

    public void whenActualPlayerIsLastThenNextPlayerIsP1() {
	when(dummyPlayers[0].getPlay()).thenReturn(dummyCoordinate);
	playerHandler.nextTurn();
	playerHandler.nextTurn();
	playerHandler.nextTurn();
	Move move = playerHandler.getPlayerMove();
	assertEquals(move.getPlayerId(), dummyPlayers[0].getId());
    }

    public void whenGetNextPlayerMoveThenPlayerMoveIsReturned() {
	Move move = new Move(dummyCoordinate, dummyPlayers[0]);
	when(playerLoader.loadPlayers()).thenReturn(dummyPlayers);
	when(dummyPlayers[0].getPlay()).thenReturn(dummyCoordinate);
	doNothing().when(writer).writeInfoMessage(anyString());
	Move result = playerHandler.getPlayerMove();
	assertEquals(result, move);
    }

    public void whenGetNextPlayerMoveThenInfoMsgIsWrote() {
	when(playerLoader.loadPlayers()).thenReturn(dummyPlayers);
	when(dummyPlayers[0].getPlay()).thenReturn(dummyCoordinate);
	doNothing().when(writer).writeInfoMessage(anyString());
	playerHandler.getPlayerMove();
	verify(writer, times(1)).writeInfoMessage(anyString());
    }

    public void whenGetPlayerByIdThenThePlayerIsReturned() {
	Player dummyPlayer = dummyPlayers[0];
	Player receivedPlayer = playerHandler.getPlayerById(dummyPlayer.getId());
	assertEquals(dummyPlayer, receivedPlayer);
    }

    public void whenGetPlayerByIdAndIdIsNotFoundThenNullIsReturned() {
	Player dummyPlayer = new DummyPlayer('d');
	Player receivedPlayer = playerHandler.getPlayerById(dummyPlayer.getId());
	assertNull(receivedPlayer);
    }

    public void whenRefreshBoardThenPlayerRefreshBoard() {
	playerHandler.refreshPlayerBoard(dummyBoard);
	verify(dummyPlayers[0], times(1)).refreshBoard(any(Board.class));
    }

    private Player[] getDummyPlayers() {
	Player p1 = mock(Player.class);
	Player p2 = mock(Player.class);
	Player p3 = mock(Player.class);
	return new Player[] { p1, p2, p3 };
    }

}
