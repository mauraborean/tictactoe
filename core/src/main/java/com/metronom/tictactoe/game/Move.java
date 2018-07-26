package com.metronom.tictactoe.game;

import com.metronom.tictactoe.player.Player;

/**
 * Immutable class that contains the required data to perform a play on the
 * board.
 * 
 * @author Maura Borean
 *
 */
public class Move {

    private Coordinate coordinate;
    private Player player;

    /**
     * Constructor with coordinate and player information.
     * 
     * @param coordinate {@link Coordinate}
     * @param player {@link Player}
     */
    public Move(Coordinate coordinate, Player player) {
	this.coordinate = coordinate;
	this.player = player;
    }

    /**
     * Returns the value that belongs to the X point.
     * 
     * @return int value
     */
    public int getX() {
	return coordinate.getX();
    }

    /**
     * Returns the value that belongs to the Y point.
     * 
     * @return int value
     */
    public int getY() {
	return coordinate.getY();
    }

    /**
     * Returns the id of the {@link Player} who performed the move.
     * 
     * @return char value
     */
    public char getPlayerId() {
	return player.getId();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Move other = (Move) obj;
	if (coordinate == null) {
	    if (other.coordinate != null) {
		return false;
	    }
	} else if (!coordinate.equals(other.coordinate)) {
	    return false;
	}
	if (player == null) {
	    if (other.player != null) {
		return false;
	    }
	} else if (!player.equals(other.player)) {
	    return false;
	}
	return true;
    }

}
