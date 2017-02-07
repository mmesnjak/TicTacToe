package com.miselmesnjak.tictactoe.domain;

import com.miselmesnjak.tictactoe.services.GameStrategy;
import lombok.Data;

/**
 * @author  Misel Mesnjak
 * @version 1.0
 * @since   2017-02-05
 */
@Data
public class Game {
    public static final int MAX_MOVES = PlayBoard.LENGTH * PlayBoard.LENGTH;

    private final int gameId;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final PlayBoard board = new PlayBoard();
    private GameStatus status = GameStatus.inProgress;
    private Player winner;
    private Player currentPlayer;
    private int moves;
    private GameStrategy aiGameStrategy;

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", firstPlayer=" + firstPlayer +
                ", secondPlayer=" + secondPlayer +
                ", board=" + board +
                ", status=" + status +
                ", winner=" + winner +
                ", currentPlayer=" + currentPlayer +
                ", moves=" + moves +
                ", aiGameStrategy=" + aiGameStrategy +
                '}';
    }

    public Game(int gameId, Player first, Player second) {
        this.gameId = gameId;
        this.firstPlayer = first;
        this.secondPlayer = second;
        this.currentPlayer = first;
    }

    public Player switchPlayer() {
        return currentPlayer = firstPlayer.equals(currentPlayer) ? secondPlayer : firstPlayer;
    }

    public void incrementMoves() {
        this.moves++;
    }

}
