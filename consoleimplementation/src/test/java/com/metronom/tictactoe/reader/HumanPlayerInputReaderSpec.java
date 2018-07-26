package com.metronom.tictactoe.reader;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.reader.InputReader;
import com.metronom.tictactoe.reader.impl.HumanPlayerInputReaderImpl;

@Test
public class HumanPlayerInputReaderSpec {

    private static final String INPUT = "Hello World!";
    private InputStream originalInputStream = System.in;
    private ByteArrayInputStream in;
    private InputReader inputReader;

    @BeforeMethod
    public void beforeMethod() {
	in = new ByteArrayInputStream(INPUT.getBytes());
	System.setIn(in);

	inputReader = new HumanPlayerInputReaderImpl();
    }

    @AfterMethod
    public void afterMethodRestoreStreams() {
	System.setIn(originalInputStream);
    }

    public void whenGetUserInputThenInputIsScanned() {
	String userInput = inputReader.getInput('a');
	assertEquals(userInput, INPUT);
    }

}
