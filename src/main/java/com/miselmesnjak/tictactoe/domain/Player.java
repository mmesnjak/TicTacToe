package com.miselmesnjak.tictactoe.domain;

import lombok.Data;

/**
 * Created by Misel on 5.2.2017
 */
@Data
public class Player implements Comparable<Player> {
    public static final String COMPUTER = "computer";
    private String name;
    private int wins;
    private int loses;
    private int draws;
    private CellValue mark;

    public Player(String name, CellValue mark) {
        this.name = name;
        this.mark = mark;
    }

    public double getWinPercentage() {
        return wins / (wins + loses + draws);
    }

    @Override
    public int compareTo(Player o) {
        return name.compareTo(o.name);
    }
}
