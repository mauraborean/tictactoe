package com.metronom.tictactoe.board;

import com.metronom.tictactoe.exception.BadInputException;
import com.metronom.tictactoe.exception.BoardSizeOutOfBoundException;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.Player;

/**
 * Represents the game board. {@code Board} allows to store and access the
 * players moves and create copies of the board.
 * 
 * @author Maura Borean
 *
 */
public class Board {

    private static final String BOARD_SIZE_ERROR_MSG = "Board size out of boundaries. Size should be in range [%d - %d] but was %d";
    private static final String BAD_INPUT_ERROR_MSG = "Invalid input. Move values should be between [0 - %d]";
    private static final String MOVE_ALREADY_PLAYED_ERROR_MSG = "Move (%d, %d) is already played";

    private Character[][] board;

    /**
     * Constructs a new board with the given parameters.
     * 
     * @param size int value with the size of the board
     * @param minSize int value with the minimum value the board size could be
     * @param maxSize int value with the maximum value the board size could be
     * @throws BoardSizeOutOfBoundException if the size is not between the min value
     *         and max value
     */
    public Board(int size, int minSize, int maxSize) throws BoardSizeOutOfBoundException {
	if (size < minSize || size > maxSize) {
	    throw new BoardSizeOutOfBoundException(String.format(BOARD_SIZE_ERROR_MSG, minSize, maxSize, size));
	}
	board = new Character[size][size];
    }

    private Board(Board b) {
	this.board = b.board;
    }

    /**
     * Validates that the given move is inside the board boundaries and that the
     * coordinate has not been played before. Then if move is valid, it is stored in
     * the board.
     * 
     * @param move {@link Move}
     * @throws BadInputException if cannot perform the play with move values
     */
    public void play(Move move) throws BadInputException {
	checkValidValues(move.getX(), move.getY());
	checkFreeMove(move.getX(), move.getY());
	board[move.getY()][move.getX()] = move.getPlayerId();
    }

    /**
     * Creates and returns a copy of the current board.
     * 
     * @return {@link Board}
     */
    public Board getCopy() {
	return new Board(this);
    }

    /**
     * Returns the board size.
     * 
     * @return int value
     */
    public int getSize() {
	return board.length;
    }

    /**
     * Looks for the {@link Player}'s id that played in the given position.
     * 
     * @param x an int with the X coordinate value
     * @param y an int with the Y coordinate value
     * @return Character with the player id or {@code null} if the coordinate is
     *         free
     */
    public Character getPlayerIdAtPosition(int x, int y) {
	return board[y][x];
    }

    private void checkValidValues(int x, int y) throws BadInputException {
	if (!isNumberBetweenValues(x, -1, getSize()) || !isNumberBetweenValues(y, -1, getSize())) {
	    String errMsg = String.format(BAD_INPUT_ERROR_MSG, getSize() - 1, x, y);
	    throw new BadInputException(errMsg);
	}
    }

    private boolean isNumberBetweenValues(int number, int min, int max) {
	return number > min && number < max;
    }

    private void checkFreeMove(int x, int y) throws BadInputException {
	if (board[y][x] != null) {
	    throw new BadInputException(String.format(MOVE_ALREADY_PLAYED_ERROR_MSG, x, y));
	}
    }

}
