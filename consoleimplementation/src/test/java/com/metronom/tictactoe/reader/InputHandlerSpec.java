package com.metronom.tictactoe.reader;

import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.player.DummyPlayer;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.reader.InputReader;
import com.metronom.tictactoe.reader.impl.InputHandlerImpl;
import com.metronom.tictactoe.validator.InputValidator;

@Test
public class InputHandlerSpec {

    private InputHandler handler;
    private InputReader reader;
    private InputValidator validator;

    private Player dummyPlayer;

    @BeforeMethod
    public void beforeMethod() {
	dummyPlayer = new DummyPlayer();
	reader = mock(InputReader.class);
	validator = mock(InputValidator.class);
	handler = new InputHandlerImpl(reader, validator);
    }

    public void whenUserInputIsCorrectThenCoordinateIsReturned() {
	final String sMove = "1,2";
	when(reader.getInput(anyChar())).thenReturn(sMove);
	doNothing().when(validator).validatePlayerInput(anyString());
	Coordinate result = handler.getPlayerCoodinate(dummyPlayer.getId());
	assertEquals(result.getX(), 1);
	assertEquals(result.getY(), 2);
    }

    public void whenUserInputIsCorrectAndIncludesBlankSpacesThenCoordinateIsReturned() {
	final String sMove = "           4   ,   5";
	when(reader.getInput(anyChar())).thenReturn(sMove);
	doNothing().when(validator).validatePlayerInput(anyString());
	Coordinate result = handler.getPlayerCoodinate(dummyPlayer.getId());
	assertEquals(result.getX(), 4);
	assertEquals(result.getY(), 5);
    }

    public void whenGetPlayerCoordinateThenInputIsValidated() {
	final String sMove = "1,2";
	when(reader.getInput(anyChar())).thenReturn(sMove);
	doNothing().when(validator).validatePlayerInput(anyString());
	handler.getPlayerCoodinate(dummyPlayer.getId());
	verify(validator, times(1)).validatePlayerInput(anyString());
    }

}
