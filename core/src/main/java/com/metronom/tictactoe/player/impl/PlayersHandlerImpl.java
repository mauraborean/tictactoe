package com.metronom.tictactoe.player.impl;

import java.util.Arrays;
import java.util.Optional;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Coordinate;
import com.metronom.tictactoe.game.Move;
import com.metronom.tictactoe.player.Player;
import com.metronom.tictactoe.player.PlayersLoader;
import com.metronom.tictactoe.player.PlayersHandler;
import com.metronom.tictactoe.writer.Writer;

public class PlayersHandlerImpl implements PlayersHandler {

    private static final String NEXT_MOVE_INFO_MSG = "Next move for player %s:";

    private Writer writer;

    private Player[] players;
    private int turn;

    public PlayersHandlerImpl(PlayersLoader playerLoader, Writer writer) {
	this.writer = writer;
	players = playerLoader.loadPlayers();
	turn = 0;
    }

    @Override
    public Player[] getPlayers() {
	return players;
    }

    @Override
    public Player getPlayerById(char id) {
	Player player = null;
	Optional<Player> optPlayer = Arrays.stream(players).filter(p -> p.getId() == id).findFirst();
	if (optPlayer.isPresent()) {
	    player = optPlayer.get();
	}
	return player;
    }

    @Override
    public void nextTurn() {
	if (++turn == players.length) {
	    turn = 0;
	}
    }

    @Override
    public Move getPlayerMove() {
	Player player = getPlayer();
	writer.writeInfoMessage(String.format(NEXT_MOVE_INFO_MSG, player.getId()));
	Coordinate coordinate = player.getPlay();
	return new Move(coordinate, player);
    }

    @Override
    public void refreshPlayerBoard(Board board) {
	getPlayer().refreshBoard(board);
    }

    private Player getPlayer() {
	return players[turn];
    }
}
