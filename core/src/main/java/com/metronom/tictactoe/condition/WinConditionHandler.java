package com.metronom.tictactoe.condition;

import com.metronom.tictactoe.board.Board;
import com.metronom.tictactoe.game.Move;

/**
 * Encapsulates the logic to determine if one or more {@link WinCondition}s are
 * met.
 * 
 * @author Maura Borean
 *
 */
public interface WinConditionHandler {

    /**
     * Evaluates the {@link WinCondition}s required to win the game.
     * 
     * @param board {@link Board} to evaluate
     * @param move {@link Move} with the last performed move
     * @return {@code true} if any of the conditions are met, {@code false} otherwise
     */
    boolean isWin(Board board, Move move);

}
