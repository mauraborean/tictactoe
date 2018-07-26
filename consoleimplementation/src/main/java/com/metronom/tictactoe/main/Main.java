package com.metronom.tictactoe.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.metronom.tictactoe.condition.GameOverCondition;
import com.metronom.tictactoe.condition.WinCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.condition.impl.Diagonal1WinCondition;
import com.metronom.tictactoe.condition.impl.Diagonal2WinCondition;
import com.metronom.tictactoe.condition.impl.GameOverConditionImpl;
import com.metronom.tictactoe.condition.impl.HorizontalWinCondition;
import com.metronom.tictactoe.condition.impl.VerticalWinCondition;
import com.metronom.tictactoe.condition.impl.WinConditionHandlerImpl;
import com.metronom.tictactoe.exception.InitializationException;
import com.metronom.tictactoe.game.Game;
import com.metronom.tictactoe.game.GameImpl;
import com.metronom.tictactoe.player.PlayersLoader;
import com.metronom.tictactoe.player.PlayersHandler;
import com.metronom.tictactoe.player.impl.PlayersLoaderImpl;
import com.metronom.tictactoe.player.impl.PlayersHandlerImpl;
import com.metronom.tictactoe.reader.ConfigurationReader;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.reader.InputReader;
import com.metronom.tictactoe.reader.impl.ConfigurationReaderImpl;
import com.metronom.tictactoe.reader.impl.HumanPlayerInputReaderImpl;
import com.metronom.tictactoe.reader.impl.InputHandlerImpl;
import com.metronom.tictactoe.validator.InputValidator;
import com.metronom.tictactoe.validator.impl.InputValidatorImpl;
import com.metronom.tictactoe.writer.Writer;
import com.metronom.tictactoe.writer.impl.ConsoleWriter;

public class Main {

    private static final String UNEXPECTED_ERROR_MSG = "Unexpected error!";
    private static final String CONFIGURATION_FILE_ERROR_MSG = "Error loading configuration input file: %s\n";
    private static final String PATH = "configuration.properties";

    public static void main(String[] args) {
	new Main().execute();
    }

    public void execute() {
	Writer writer = new ConsoleWriter();

	try {
	    printGameBanner(writer);

	    InputValidator inputValidator = new InputValidatorImpl();
	    ConfigurationReader configurationReader = new ConfigurationReaderImpl(inputValidator, getProperties());

	    List<WinCondition> winConditions = new ArrayList<>();
	    winConditions.add(new VerticalWinCondition());
	    winConditions.add(new HorizontalWinCondition());
	    winConditions.add(new Diagonal1WinCondition());
	    winConditions.add(new Diagonal2WinCondition());

	    WinConditionHandler winCondition = new WinConditionHandlerImpl(winConditions);

	    GameOverCondition gameOverCondition = new GameOverConditionImpl();

	    InputReader inputReader = new HumanPlayerInputReaderImpl();
	    InputHandler inputHandler = new InputHandlerImpl(inputReader, inputValidator);
	    PlayersLoader playerLoader = new PlayersLoaderImpl(configurationReader, inputValidator, inputHandler, writer);
	    PlayersHandler playerHandler = new PlayersHandlerImpl(playerLoader, writer);

	    Game game = new GameImpl(configurationReader, winCondition, gameOverCondition, playerHandler, writer);
	    game.run();
	} catch (InitializationException e) {
	    writer.writeErrorMessage(e.getMessage());
	} catch (RuntimeException e) {
	    writer.writeErrorMessage(UNEXPECTED_ERROR_MSG);
	}
    }

    private void printGameBanner(Writer writer) {
	String banner = "\r\n" + " _______  ___   _______         _______  _______  _______         _______  _______  _______ \r\n"
		+ "|       ||   | |       |       |       ||   _   ||       |       |       ||       ||       |\r\n"
		+ "|_     _||   | |       | ____  |_     _||  |_|  ||       | ____  |_     _||   _   ||    ___|\r\n"
		+ "  |   |  |   | |       ||____|   |   |  |       ||       ||____|   |   |  |  | |  ||   |___ \r\n"
		+ "  |   |  |   | |      _|         |   |  |       ||      _|         |   |  |  |_|  ||    ___|\r\n"
		+ "  |   |  |   | |     |_          |   |  |   _   ||     |_          |   |  |       ||   |___ \r\n"
		+ "  |___|  |___| |_______|         |___|  |__| |__||_______|         |___|  |_______||_______|\r\n" + "";

	writer.writeInfoMessage(banner);
    }

    private Properties getProperties() {
	Properties properties = new Properties();
	try {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream input = classLoader.getResourceAsStream(PATH);
	    properties.load(input);
	} catch (IOException e) {
	    throw new InitializationException(String.format(CONFIGURATION_FILE_ERROR_MSG, e.getMessage()));
	}
	return properties;
    }

}