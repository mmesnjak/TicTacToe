package com.miselmesnjak.tictactoe.services;

/**
 * Created by Misel on 5.2.2017.
 */
public class IllegalGameId extends Throwable {
    public IllegalGameId(String message) {
        super(message);
    }

    public IllegalGameId(int gameId) {
        super(String.format("Game with Id %d does not exist!", gameId));
    }
}
