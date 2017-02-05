package com.miselmesnjak.tictactoe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Misel on 5.2.2017.
 */
@Data
@AllArgsConstructor
public class Cell {
    private String player;
    private CellValue value;

    @Override
    public String toString() {
        return "Cell{" +
                "player='" + player + '\'' +
                ", value=" + value +
                '}';
    }
}
