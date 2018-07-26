package com.metronom.tictactoe.game;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.GameOverCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.exception.PlayException;
import com.metronom.tictactoe.player.PlayersHandler;
import com.metronom.tictactoe.writer.Writer;

/**
 * Creates and manages the game implementation. The class is responsible of the
 * player's interaction with the board, to check the game status and handle
 * errors that may occur once the game has started.
 * 
 * @author Maura Borean
 *
 */
public abstract class Game {

    private Board board;
    private WinConditionHandler winConditionHandler;
    private GameOverCondition gameOverCondition;
    private PlayersHandler playersHandler;
    private Writer writer;

    /**
     * Constructs a {@code Game} instance.
     * 
     * @param board {@link Board}
     * @param winCondition {@link WinConditionHandler}
     * @param gameOverCondition {@link GameOverCondition}
     * @param playerHandler {@link PlayersHandler}
     * @param writer {@link Writer}
     */
    public Game(Board board, WinConditionHandler winCondition, GameOverCondition gameOverCondition, PlayersHandler playerHandler, Writer writer) {
	this.board = board;
	this.winConditionHandler = winCondition;
	this.gameOverCondition = gameOverCondition;
	this.playersHandler = playerHandler;
	this.writer = writer;
    }

    /**
     * Starts the game.
     */
    public void run() {
	Move move = null;
	Status status = Status.PLAYING;

	do {
	    try {
		playersHandler.refreshPlayerBoard(board);
		move = playersHandler.getPlayerMove();
		board.play(move);
		status = getGameStatus(move);
		playersHandler.nextTurn();
	    } catch (PlayException e) {
		writer.writeErrorMessage(e.getMessage());
	    }
	} while (status.continuePlaying());

	playersHandler.refreshPlayerBoard(board);
	printEndGameMessage(status, move.getPlayerId());
    }

    private Status getGameStatus(Move move) {
	Status status = Status.PLAYING;

	if (winConditionHandler.isWin(board, move)) {
	    status = Status.WON;
	} else if (gameOverCondition.isGameOver(board)) {
	    status = Status.GAME_OVER;
	}

	return status;
    }

    private void printEndGameMessage(Status status, int playerId) {
	switch (status) {
	case WON:
	    writer.writeInfoMessage(String.format(status.getMsg(), playerId));
	    break;
	case GAME_OVER:
	    writer.writeInfoMessage(status.getMsg());
	    break;
	default:
	    break;
	}
    }
}
