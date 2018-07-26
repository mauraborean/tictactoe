package com.metronom.tictactoe.writer.impl;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.writer.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void writeInfoMessage(String msg) {
	if (msg != null) {
	    System.out.println(msg);
	}
    }

    @Override
    public void writeErrorMessage(String msg) {
	if (msg != null) {
	    System.err.println(msg);
	}
    }

    @Override
    public void printBoard(Board board) {
	System.out.println();
	String lineSeparator = getBoardLineSeparator(board.getSize());
	for (int y = 0; y < board.getSize(); ++y) {
	    String boardLine = getBoardStringAtLine(board, y);
	    printBoardLine(boardLine, lineSeparator, isLastLine(board.getSize(), y));
	}
	System.out.println();
    }

    private String getBoardLineSeparator(int size) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < size; ++i) {
	    sb.append("---+");
	}
	final int lastPlusPos = sb.lastIndexOf("+");
	sb.deleteCharAt(lastPlusPos);
	return sb.toString();
    }

    private String getBoardStringAtLine(Board board, int y) {
	StringBuilder sb = new StringBuilder();
	for (int x = 0; x < board.getSize(); ++x) {
	    Character playerId = board.getPlayerIdAtPosition(x, y);
	    sb.append(" ");
	    sb.append(playerId == null ? " " : playerId);
	    sb.append(" |");
	}

	final int lastPipePos = sb.lastIndexOf("|");
	sb.deleteCharAt(lastPipePos);
	return sb.toString();
    }

    private void printBoardLine(String boardLine, String lineSeparator, boolean lastLine) {
	System.out.println(boardLine);
	if (!lastLine) {
	    System.out.println(lineSeparator);
	}
    }

    private boolean isLastLine(int boardSize, int line) {
	return line == boardSize - 1;
    }

}
