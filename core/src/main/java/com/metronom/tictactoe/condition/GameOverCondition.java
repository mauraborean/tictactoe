package com.metronom.tictactoe.condition;

import com.metronom.tictactoe.board.Board;

/**
 * Encapsulates the logic to determine if the game has ended with no winners.
 * 
 * @author Maura Borean
 *
 */
public interface GameOverCondition {

    /**
     * Evaluates if the board does not have any available moves left.
     * 
     * @param board the {@link Board} to evaluate
     * @return {@code true} if game is over, {@code false} otherwise
     */
    boolean isGameOver(Board board);

}
