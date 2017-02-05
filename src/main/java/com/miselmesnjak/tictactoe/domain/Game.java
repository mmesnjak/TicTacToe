package com.miselmesnjak.tictactoe.domain;

import lombok.Data;

/**
 * Created by Misel on 5.2.2017.
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

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", firstPlayer=" + firstPlayer +
                ", secondPlayer=" + secondPlayer +
                ", board=" + board +
                ", status=" + status +
                ", winner=" + winner +
                '}';
    }
}
