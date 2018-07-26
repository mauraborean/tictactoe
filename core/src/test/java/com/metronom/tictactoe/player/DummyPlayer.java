package com.metronom.tictactoe.player;

import java.util.Random;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;

public class DummyPlayer implements Player {

    private char id;

    public DummyPlayer() {
	this.id = (char) new Random().nextInt(27);
    }

    public DummyPlayer(char id) {
	super();
	this.id = id;
    }

    public char getId() {
	return id;
    }

    @Override
    public Coordinate getPlay() {
	return new Coordinate(0, 0);
    }

    @Override
    public void refreshBoard(Board board) {
	/*
	 * Dummy refresh
	 */
    }
}
