package com.miselmesnjak.tictactoe.domain;

import lombok.Data;

/**
 * Created by Misel on 5.2.2017.
 */
@Data
public class PlayBoard {
    public static final int LENGTH = 3;
    private final Cell[][] cells = new Cell[LENGTH][LENGTH];
}
