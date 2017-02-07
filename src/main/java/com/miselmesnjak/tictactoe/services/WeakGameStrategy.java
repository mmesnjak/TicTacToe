package com.miselmesnjak.tictactoe.services;

import com.miselmesnjak.tictactoe.domain.Cell;
import com.miselmesnjak.tictactoe.domain.PlayBoard;
import com.miselmesnjak.tictactoe.domain.Player;

import java.util.*;

/**
 * @author Misel Mesnjak
 * @version 1.0
 * @since 2017-02-07
 */
public class WeakGameStrategy implements GameStrategy {
    @Override
    public Cell calculateMove(PlayBoard board, Player player) throws IllegalMove {
        List<Cell> cells = new ArrayList<>();

        int i = 0;
        for (Cell[] row : board.getCells()) {
            int j = 0;
            for (Cell c : row) {
                if (c == null) {
                    c = new Cell(player.getName(), player.getMark(), i, j);
                    cells.add(c);
                }
                j++;
            }
            i++;
        }

        if (cells.size() == 0) throw new IllegalMove("No possible moves to calculate!");

        // Randomly choose one of the possible moves
        Cell result = (Cell) cells.toArray()[new Random().nextInt(cells.size())];

        return result;
    }
}
