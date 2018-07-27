package com.metronom.tictactoe.validator;

import com.metronom.tictactoe.exception.BadInputException;
import com.metronom.tictactoe.exception.InitializationException;
import com.metronom.tictactoe.exception.PlayException;

/**
 * Provides the structure for the game's necessary input validations.<br>
 * <br>
 * The class implementing {@code InputValidator} validates different inputs
 * received for initialization or player moves and returns the errors as
 * {@link PlayException}.
 * 
 * @author Maura Borean
 *
 */
public interface InputValidator {

    String INVALID_INPUT_ERROR_MSG = "Invalid input. Correct pattern should be: x,y\n";
    String INVALID_PLAYER_ID_ERROR_MSG = "Repeated player id for players with id:[%s]\n";
    String INVALID_BOARD_SIZE_ERROR_MSG = "Invalid board size. A number is expected\n";

    /**
     * Validates if the player's input is correct.
     * 
     * @param input a String with the input entered by the player
     * @throws BadInputException if input is not correct
     */
    void validatePlayerInput(String input) throws BadInputException;

    /**
     * Checks if the players id's are valid.
     * 
     * @param playerIds a char array with the id's to validate
     * @throws InitializationException if the id's are not valid
     */
    void validatePlayersIds(char... playerIds) throws InitializationException;

    /**
     * Checks whether the value received as the board size is valid.
     * 
     * @param size a String with the received board size
     * @throws InitializationException if the board size is not valid
     */
    void validateBoardSize(String size) throws InitializationException;

}
