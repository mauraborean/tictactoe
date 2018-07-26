package com.metronom.tictactoe.player;

import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.player.PlayersLoader;
import com.metronom.tictactoe.player.impl.AIPlayer;
import com.metronom.tictactoe.player.impl.HumanPlayer;
import com.metronom.tictactoe.player.impl.PlayersLoaderImpl;
import com.metronom.tictactoe.reader.ConfigurationReader;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.validator.InputValidator;
import com.metronom.tictactoe.writer.Writer;

@Test
public class PlayersLoaderSpec {

    private PlayersLoader playersLoader;
    private ConfigurationReader configuratonReader;
    private InputValidator inputValidator;
    private InputHandler inputHandler;
    private Writer writer;

    @BeforeMethod
    public void beforeMethod() {
	configuratonReader = mock(ConfigurationReader.class);
	inputValidator = mock(InputValidator.class);
	inputHandler = mock(InputHandler.class);
	writer = mock(Writer.class);
	playersLoader = new PlayersLoaderImpl(configuratonReader, inputValidator, inputHandler, writer);
    }

    public void whenPlayersAreLoadedThen3PlayersAreFound() {
	when(configuratonReader.getPlayerId(anyString())).thenReturn('a', 'b', 'c');
	doNothing().when(inputValidator).validatePlayersIds(anyChar(), anyChar(), anyChar());
	Player[] players = playersLoader.loadPlayers();

	assertNotNull(players);
	assertTrue(players.length == 3);
    }

    public void whenPlayersAreLoadedThen2HumanPlayersAreFound() {
	when(configuratonReader.getPlayerId(anyString())).thenReturn('a', 'b', 'c');
	doNothing().when(inputValidator).validatePlayersIds(anyChar(), anyChar(), anyChar());
	Player[] players = playersLoader.loadPlayers();

	long humanPlayers = Arrays.stream(players).filter(w -> (w instanceof HumanPlayer)).count();
	assertEquals(humanPlayers, 2);
    }

    public void whenPlayersAreLoadedThen1ComputerPlayerIsLoaded() {
	when(configuratonReader.getPlayerId(anyString())).thenReturn('a', 'b', 'c');
	doNothing().when(inputValidator).validatePlayersIds(anyChar(), anyChar(), anyChar());
	Player[] players = playersLoader.loadPlayers();

	long computerPlayer = Arrays.stream(players).filter(w -> (w instanceof AIPlayer)).count();
	assertEquals(computerPlayer, 1);
    }

    public void whenPlayersAreLoadedThenPlayersIdsAreRead3Times() {
	when(configuratonReader.getPlayerId(anyString())).thenReturn('a', 'b', 'c');
	doNothing().when(inputValidator).validatePlayersIds(anyChar(), anyChar(), anyChar());
	playersLoader.loadPlayers();
	verify(configuratonReader, times(3)).getPlayerId(anyString());
    }

    public void whenPlayerIdsAreReadThenValidationIsCalled() {
	when(configuratonReader.getPlayerId(anyString())).thenReturn('a', 'b', 'c');
	doNothing().when(inputValidator).validatePlayersIds(anyChar(), anyChar(), anyChar());
	playersLoader.loadPlayers();
	verify(inputValidator, times(1)).validatePlayersIds(anyChar(), anyChar(), anyChar());
    }
}
