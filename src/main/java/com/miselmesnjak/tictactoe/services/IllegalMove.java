package com.miselmesnjak.tictactoe.services;

/**
 * Created by Misel on 5.2.2017.
 */
public class IllegalMove extends Throwable {
    public IllegalMove(String message) {
        super(message);
    }
}
