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
    private boolean isComputer = false;

    public Player(String name) {
        this.name = name;
        this.isComputer = COMPUTER.toLowerCase().equals(name.toLowerCase());
    }

    public double getWinPercentage() {
        return (wins + loses + draws) == 0 ? 0 : wins * 100 / (wins + loses + draws);
    }

    @Override
    public int compareTo(Player o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return name.equalsIgnoreCase(player.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
