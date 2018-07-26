package com.metronom.tictactoe.reader.impl;

import java.util.Properties;

import com.metronom.tictactoe.exception.InitializationException;
import com.metronom.tictactoe.reader.ConfigurationReader;
import com.metronom.tictactoe.validator.InputValidator;

public class ConfigurationReaderImpl implements ConfigurationReader {

    private static final String PLAYER_ID_NOT_FOUND_ERROR_MSG = "Player id for player [%s] not found\n";
    private static final String BOARD_SIZE_NOT_FOUND_ERROR_MSG = "Board size not found\n";
    private static final String BOARD_SIZE_KEY = "playgroundSize";

    private InputValidator inputValidator;
    private Properties properties;

    public ConfigurationReaderImpl(InputValidator inputValidator, Properties properties) throws InitializationException {
	this.inputValidator = inputValidator;
	this.properties = properties;
    }

    @Override
    public char getPlayerId(String key) throws InitializationException {
	String id = properties.getProperty(key);
	if (id == null) {
	    throw new InitializationException(String.format(PLAYER_ID_NOT_FOUND_ERROR_MSG, key));
	}
	return id.charAt(0);
    }

    @Override
    public int getBoardSize() throws InitializationException {
	String boardSize = properties.getProperty(BOARD_SIZE_KEY);
	if (boardSize == null) {
	    throw new InitializationException(BOARD_SIZE_NOT_FOUND_ERROR_MSG);
	}
	inputValidator.validateBoardSize(boardSize);
	return Integer.parseInt(boardSize);
    }

}
