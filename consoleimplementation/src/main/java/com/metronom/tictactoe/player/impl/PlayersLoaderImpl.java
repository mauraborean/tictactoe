
package com.metronom.tictactoe.player.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.player.PlayersLoader;
import com.metronom.tictactoe.reader.ConfigurationReader;
import com.metronom.tictactoe.reader.InputHandler;
import com.metronom.tictactoe.validator.InputValidator;
import com.metronom.tictactoe.writer.Writer;

public class PlayersLoaderImpl implements PlayersLoader {

    private static final String PLAYER_1 = "humanPlayer1.id";
	private static final String PLAYER_2 = "humanPlayer2.id";
	private static final String PLAYER_3 = "computerPlayer.id";

    private ConfigurationReader configurationReader;
    private InputValidator inputValidator;
    private InputHandler inputHandler;
    private Writer writer;

    public PlayersLoaderImpl(ConfigurationReader configurationReader, InputValidator inputValidator, InputHandler inputHandler, Writer writer) {
	this.configurationReader = configurationReader;
	this.inputValidator = inputValidator;
	this.inputHandler = inputHandler;
	this.writer = writer;
    }

    @Override
    public Player[] loadPlayers() {
	List<Player> players = new ArrayList<>();

	char player1 = configurationReader.getPlayerId(PLAYER_1);
	char player2 = configurationReader.getPlayerId(PLAYER_2);
	char player3 = configurationReader.getPlayerId(PLAYER_3);
	inputValidator.validatePlayersIds(player1, player2, player3);

	players.add(new HumanPlayer(player1, inputHandler, writer));
	players.add(new HumanPlayer(player2, inputHandler, writer));
	players.add(new AIPlayer(player3, writer));

	Collections.shuffle(players);

	return players.toArray(new Player[0]);
    }

}
