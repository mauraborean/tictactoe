package com.metronom.tictactoe.reader;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.exception.InitializationException;
import com.metronom.tictactoe.reader.impl.ConfigurationReaderImpl;
import com.metronom.tictactoe.validator.InputValidator;

@Test
public class ConfigurationReaderSpec {

    private static final String INVALID_KEY = "wrongKey.id";

    private ConfigurationReader reader;
    private Properties properties;
    private InputValidator inputValidator;

    @BeforeMethod
    public void beforeMethod() {
	properties = mock(Properties.class);
	inputValidator = mock(InputValidator.class);
	reader = new ConfigurationReaderImpl(inputValidator, properties);
    }

    public void whenGetPlayerIdThenSearchInProperties() {
	when(properties.getProperty(anyString())).thenReturn("X");
	reader.getPlayerId("dummyKey");
	verify(properties, times(1)).getProperty(anyString());
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenGetPlayerIdIAndKeyIsNotFoundThenRuntimeException() {
	when(properties.getProperty(INVALID_KEY)).thenReturn(null);
	reader.getPlayerId(INVALID_KEY);
    }

    public void whenGetBoardSizeThenBoardSizeIsReturned() {
	final String boardSize = "3";
	when(properties.getProperty(anyString())).thenReturn(boardSize);
	int intBoardSize = reader.getBoardSize();
	assertEquals(intBoardSize, Integer.parseInt(boardSize));
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenBoardSizePropertyNotFoundThenInitializationException() {
	when(properties.getProperty(anyString())).thenReturn(null);
	reader.getBoardSize();
    }

    @Test(expectedExceptions = { InitializationException.class })
    public void whenBoardSizePropertyNotANumberThenInitializationException() {
	when(properties.getProperty(anyString())).thenReturn("a");
	doThrow(InitializationException.class).when(inputValidator).validateBoardSize(anyString());
	reader.getBoardSize();
    }

}
