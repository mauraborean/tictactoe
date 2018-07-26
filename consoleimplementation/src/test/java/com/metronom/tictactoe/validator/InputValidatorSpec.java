package com.metronom.tictactoe.validator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.exception.BadInputException;
import com.metronom.tictactoe.exception.InitializationException;
import com.metronom.tictactoe.validator.InputValidator;
import com.metronom.tictactoe.validator.impl.InputValidatorImpl;

@Test
public class InputValidatorSpec {

    private InputValidator inputValidator;

    @BeforeMethod
    public void beforeMethod() {
	inputValidator = new InputValidatorImpl();
    }

    public void whenInputAreTwoIntegersSeparatedByCommaThenSuccess() {
	inputValidator.validatePlayerInput("1,3");
    }

    public void whenInputAreTwoIntegersSeparatedByCommaWithSpacesThenSuccess() {
	inputValidator.validatePlayerInput("1    ,     3");
    }

    @Test(expectedExceptions = { BadInputException.class })
    public void whenInputIsNotAnIntegerThenBadInputException() {
	inputValidator.validatePlayerInput("1, message");
    }

    public void whenPlayerIdsAreCorrectAndValidateThenSuccess() {
	inputValidator.validatePlayersIds('a', 'b', 'c');
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenPlayerIds1And3AreRepeatedAndValidateThenInitializationException() {
	inputValidator.validatePlayersIds('a', 'b', 'a');
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenPlayerIds1And2AreRepeatedAndValidateThenInitializationException() {
	inputValidator.validatePlayersIds('a', 'a', 'c');
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenPlayerIds2And3AreRepeatedAndValidateThenInitializationException() {
	inputValidator.validatePlayersIds('a', 'b', 'b');
    }

    public void whenValidateCorrectBoardSizeThenSuccess() {
	inputValidator.validateBoardSize("3");
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenValidateNotANumberBoardSizeThenInitializationException() {
	inputValidator.validateBoardSize("aa");
    }
}
