package com.miselmesnjak.tictactoe.dto;

import com.miselmesnjak.tictactoe.domain.Cell;
import com.miselmesnjak.tictactoe.domain.Game;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Misel on 5.2.2017.
 */
@Data
public class GameDTO {
    protected int gameId;
    protected String status;
    protected List<CellDTO> game = new ArrayList<>();

    public GameDTO(Game game) {
        this.gameId = game.getGameId();
        this.status = game.getStatus().toString();
        Cell[][] cells = game.getBoard().getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                if (cells[row][col] != null) {
                    this.game.add(new CellDTO(row + 1, col + 1, cells[row][col].getValue().toString()));
                }
            }
        }
    }
}
