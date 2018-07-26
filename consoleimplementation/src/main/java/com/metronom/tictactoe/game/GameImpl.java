package com.metronom.tictactoe.game;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.condition.GameOverCondition;
import com.metronom.tictactoe.condition.WinConditionHandler;
import com.metronom.tictactoe.player.PlayersHandler;
import com.metronom.tictactoe.reader.ConfigurationReader;
import com.metronom.tictactoe.writer.Writer;

public class GameImpl extends Game {

    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 10;

    public GameImpl(ConfigurationReader configurationReader, WinConditionHandler winCondition, GameOverCondition gameOverCondition, PlayersHandler playerHandler, Writer writer) {
	super(getBoard(configurationReader), winCondition, gameOverCondition, playerHandler, writer);
    }

    private static Board getBoard(ConfigurationReader configurationReader) {
	int boardSize = configurationReader.getBoardSize();
	return new Board(boardSize, MIN_BOARD_SIZE, MAX_BOARD_SIZE);
    }
}
