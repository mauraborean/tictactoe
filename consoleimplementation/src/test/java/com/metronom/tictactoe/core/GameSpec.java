package com.metronom.tictactoe.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.condition.GameOverCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.game.GameImpl;
import com.metronom.tictactoe.player.PlayersHandler;
import com.metronom.tictactoe.reader.ConfigurationReader;
import com.metronom.tictactoe.writer.Writer;

@Test
public class GameSpec {

    private WinConditionHandler winConditionHandler;
    private GameOverCondition gameOverCondition;
    private PlayersHandler playerHandler;
    private Writer writer;
    private ConfigurationReader configurationReader;

    @BeforeMethod
    public void beforeMethod() {
	winConditionHandler = mock(WinConditionHandler.class);
	gameOverCondition = mock(GameOverCondition.class);
	playerHandler = mock(PlayersHandler.class);
	writer = mock(Writer.class);
	configurationReader = mock(ConfigurationReader.class);

    }

    public void whenInstantiatedThenBoardSizeIsRead() {
	when(configurationReader.getBoardSize()).thenReturn(3);
	new GameImpl(configurationReader, winConditionHandler, gameOverCondition, playerHandler, writer);
	verify(configurationReader, times(1)).getBoardSize();
    }

}
