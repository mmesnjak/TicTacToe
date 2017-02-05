package com.miselmesnjak.tictactoe.dto;

import com.miselmesnjak.tictactoe.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Mesh on 5.2.2017.
 */
@Data
@AllArgsConstructor
public class NewGameDTO {
    private int gameId;

    public NewGameDTO(Game game) {
        gameId = game.getGameId();
    }
}
