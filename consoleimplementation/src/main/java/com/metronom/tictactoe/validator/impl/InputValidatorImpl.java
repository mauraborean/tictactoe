package com.metronom.tictactoe.validator.impl;

import java.util.regex.Pattern;

import com.metronom.tictactoe.exception.BadInputException;
import com.metronom.tictactoe.exception.InitializationException;
import com.metronom.tictactoe.validator.InputValidator;

public class InputValidatorImpl implements InputValidator {

    private final static String MOVE_INPUT_PATTERN = "\\s*-?\\d+\\s*,\\s*-?\\d+\\s*";
    private final static String BOARD_SIZE_PATTERN = "\\d*";

    @Override
    public void validatePlayerInput(String input) throws BadInputException {
	if (!isValidInput(MOVE_INPUT_PATTERN, input)) {
	    throw new BadInputException(INVALID_INPUT_ERROR_MSG);
	}
    }

    @Override
    public void validatePlayersIds(char... players) throws InitializationException {
	char p1 = players[0];
	char p2 = players[1];
	char p3 = players[2];

	if (Character.compare(p1, p2) == 0) {
	    String msg = String.format(INVALID_PLAYER_ID_ERROR_MSG, p1);
	    throw new InitializationException(msg);
	}
	if (Character.compare(p1, p3) == 0) {
	    String msg = String.format(INVALID_PLAYER_ID_ERROR_MSG, p1);
	    throw new InitializationException(msg);
	}
	if (Character.compare(p2, p3) == 0) {
	    String msg = String.format(INVALID_PLAYER_ID_ERROR_MSG, p2);
	    throw new InitializationException(msg);
	}
    }

    @Override
    public void validateBoardSize(String size) throws InitializationException {
	if (!isValidInput(BOARD_SIZE_PATTERN, size)) {
	    throw new InitializationException(INVALID_BOARD_SIZE_ERROR_MSG);
	}
    }

    private boolean isValidInput(String sPattern, String input) {
	Pattern pattern = Pattern.compile(sPattern);
	return pattern.matcher(input).matches();
    }

}
