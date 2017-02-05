package com.miselmesnjak.tictactoe.dto;

import com.miselmesnjak.tictactoe.domain.Game;
import lombok.Data;

/**
 * Created by Misel on 5.2.2017.
 */
@Data
public class FinishedGameDTO extends GameDTO {
    private String winner;

    FinishedGameDTO(Game game) {
        super(game);
    }
}
