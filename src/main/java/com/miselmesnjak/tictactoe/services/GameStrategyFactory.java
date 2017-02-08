package com.miselmesnjak.tictactoe.services;

import com.miselmesnjak.tictactoe.domain.Player;

import java.util.Random;

/**
 * @author Misel Mesnjak
 * @version 1.0
 * @since 2017-02-08
 */
public class GameStrategyFactory {

    private static GameStrategy getRandomGameStrategy() {
        Random r = new Random();

        return r.nextBoolean() ? new WeakGameStrategy() : new StrongGameStrategy();
    }

    public static GameStrategy create(Player humanPlayer) {
        GameStrategy aiGameStrategy;

        if (humanPlayer.getWinPercentage() <= 0.3) aiGameStrategy = new WeakGameStrategy();
        else if (humanPlayer.getWinPercentage() <= 0.9) aiGameStrategy = getRandomGameStrategy();
        else aiGameStrategy = new StrongGameStrategy();

        return aiGameStrategy;
    }
}
