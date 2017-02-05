package com.miselmesnjak.tictactoe.services;

/**
 * Created by Misel on 5.2.2017.
 */
public class IllegalPlayerConfiguration extends Exception {
    private static final String DEFAULT_MESSAGE = "Illegal Player Configuration!";

    public IllegalPlayerConfiguration(String message) {
        super(message);
    }

    public IllegalPlayerConfiguration() {
        super(DEFAULT_MESSAGE);
    }
}
