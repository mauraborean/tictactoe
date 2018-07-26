package com.metronom.tictactoe.writer;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.DummyPlayer;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.writer.Writer;
import com.metronom.tictactoe.writer.impl.ConsoleWriter;

@Test
public class ConsoleWriterSpec {

    private static final String NEW_LINE = "\r\n";

    private Writer consoleWriter;

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeMethod
    public void beforeMethod() {
	outContent = new ByteArrayOutputStream();
	errContent = new ByteArrayOutputStream();
	System.setOut(new PrintStream(outContent));
	System.setErr(new PrintStream(errContent));
	consoleWriter = new ConsoleWriter();
    }

    @AfterMethod
    public void afterMethodRestoreStreams() {
	System.setOut(originalOut);
	System.setErr(originalErr);
    }

    public void whenInfoMessageIsSentToWriterThenIsPrintedInConsole() {
	final String msg = "Hello world!";
	consoleWriter.writeInfoMessage(msg);
	assertEquals(outContent.toString(), msg + NEW_LINE);
    }

    public void whenInfoMessageIsSentAndMsgIsNullThenNothingIsPrintedInConsole() {
	final String msg = null;
	consoleWriter.writeInfoMessage(msg);
	assertEquals(outContent.toString(), "");
    }

    public void whenErrorMessageIsSentToWriterThenIsPrintedInConsole() {
	final String msg = "Error world!";
	consoleWriter.writeErrorMessage(msg);
	assertEquals(errContent.toString(), msg + NEW_LINE);
    }

    public void whenErrorMessageIsSentAndMsgIsNullThenNothingIsPrintedInConsole() {
	final String msg = null;
	consoleWriter.writeErrorMessage(msg);
	assertEquals(errContent.toString(), "");
    }

    public void whenPrintBoardThenBoardIsPrinted() {
	Board board = getBoard();
	consoleWriter.printBoard(board);
	assertEquals(outContent.toString(), getBoardAsString(board));
    }

    private Board getBoard() {
	Board board = new Board(3, 3, 10);
	Player dummyPlayer1 = new DummyPlayer('a');
	Player dummyPlayer2 = new DummyPlayer('b');
	board.play(new Move(new Coordinate(1, 0), dummyPlayer1));
	board.play(new Move(new Coordinate(0, 2), dummyPlayer2));
	board.play(new Move(new Coordinate(2, 1), dummyPlayer1));
	board.play(new Move(new Coordinate(1, 1), dummyPlayer2));
	return board;
    }

    private String getBoardAsString(Board board) {
	String s = NEW_LINE;
	s += "   | a |   \r\n";
	s += "---+---+---\r\n";
	s += "   | b | a \r\n";
	s += "---+---+---\r\n";
	s += " b |   |   \r\n\r\n";
	return s;
    }

}