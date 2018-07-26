package com.metronom.tictactoe.player.impl;

import java.util.Random;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.writer.Writer;

/**
 * AI representation of a {@link Player}.
 * 
 * @author Maura Borean
 *
 */
public class AIPlayer implements Player {

    private char id;
    private Board board;
    private Writer writer;

    public AIPlayer(char id, Writer writer) {
	this.id = id;
	this.writer = writer;
    }

    @Override
    public char getId() {
	return id;
    }

    @Override
    public Coordinate getPlay() {
	Coordinate coordinate;

	if (new Random().nextBoolean()) {
	    coordinate = getDecreasingCoordinate();
	} else {
	    coordinate = getIncreasingCoordinate();
	}

	return coordinate;
    }

    @Override
    public void refreshBoard(Board board) {
	writer.printBoard(board);
	this.board = board;
    }

    private Coordinate getDecreasingCoordinate() {
	int x = -1;
	int y = board.getSize();
	while (x == -1 && --y > -1) {
	    x = getX(y);
	}
	return new Coordinate(x, y);
    }

    private Coordinate getIncreasingCoordinate() {
	int x = -1;
	int y = -1;
	while (x == -1 && ++y < board.getSize()) {
	    x = getX(y);
	}
	return new Coordinate(x, y);
    }

    private int getX(int y) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < board.getSize(); ++i) {
	    Character c = board.getPlayerIdAtPosition(i, y);
	    sb.append(c == null ? ' ' : c);
	}

	return getRandomXValue(sb);
    }

    private int getRandomXValue(StringBuilder sb) {
	int x;
	if (new Random().nextBoolean()) {
	    x = sb.lastIndexOf(" ");
	} else {
	    x = sb.indexOf(" ");
	}
	return x;
    }

}
