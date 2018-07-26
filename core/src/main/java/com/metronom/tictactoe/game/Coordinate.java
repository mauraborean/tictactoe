package com.metronom.tictactoe.game;

/**
 * Immutable class that encapsulates the necessary data to create a board
 * coordinate.
 * 
 * @author Maura Borean
 *
 */
public class Coordinate {

    private int x;
    private int y;

    /**
     * Constructor with the coordinate's values
     * 
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
	super();
	this.x = x;
	this.y = y;
    }

    /**
     * X value
     * 
     * @return int value
     */
    public int getX() {
	return x;
    }

    /**
     * Y value
     * 
     * @return int value
     */
    public int getY() {
	return y;
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Coordinate)) {
	    return false;
	}

	Coordinate c = (Coordinate) obj;
	if (c.x != this.x) {
	    return false;
	}
	if (c.y != this.y) {
	    return false;
	}
	return true;
    }

}
