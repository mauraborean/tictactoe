package com.metronom.tictactoe.player.impl;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.writer.Writer;

/**
 * User representation of a {@link Player}.
 * 
 * @author Maura Borean
 *
 */
public class HumanPlayer implements Player {

    private char id;
    private InputHandler inputHandler;
    private Writer writer;

    public HumanPlayer(char id, InputHandler inputHandler, Writer writer) {
	this.id = id;
	this.inputHandler = inputHandler;
	this.writer = writer;
    }

    @Override
    public char getId() {
	return id;
    }

    @Override
    public Coordinate getPlay() {
	return inputHandler.getPlayerCoodinate(id);
    }

    @Override
    public void refreshBoard(Board board) {
	writer.printBoard(board);
    }
}
