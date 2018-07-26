package com.metronom.tictactoe.reader.impl;

import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.reader.InputReader;
import com.metronom.tictactoe.validator.InputValidator;

public class InputHandlerImpl implements InputHandler {

    private InputReader inputReader;
    private InputValidator inputValidator;

    public InputHandlerImpl(InputReader inputReader, InputValidator inputValidator) {
	this.inputReader = inputReader;
	this.inputValidator = inputValidator;
    }

    @Override
    public Coordinate getPlayerCoodinate(char playerId) {
	String playerInput = inputReader.getInput(playerId);
	inputValidator.validatePlayerInput(playerInput);
	return buildCoordinate(playerInput);
    }

    private Coordinate buildCoordinate(String input) {
	String[] inputArr = input.split(",");

	int x = Integer.parseInt(inputArr[0].trim());
	int y = Integer.parseInt(inputArr[1].trim());

	return new Coordinate(x, y);
    }
}
