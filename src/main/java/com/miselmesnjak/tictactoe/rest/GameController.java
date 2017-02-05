package com.miselmesnjak.tictactoe.rest;

import com.miselmesnjak.tictactoe.domain.Game;
import com.miselmesnjak.tictactoe.dto.ErrorDTO;
import com.miselmesnjak.tictactoe.dto.GameDTO;
import com.miselmesnjak.tictactoe.dto.NewGameDTO;
import com.miselmesnjak.tictactoe.services.GameService;
import com.miselmesnjak.tictactoe.services.IllegalGameId;
import com.miselmesnjak.tictactoe.services.IllegalMove;
import com.miselmesnjak.tictactoe.services.IllegalPlayerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Misel on 5.2.2017.
 */
@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/new", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> newGame(@RequestParam(name = "first", required = false) String first,
                                     @RequestParam(name = "second", required = false) String second) {
        Game newGame;
        try {
            newGame = gameService.createNewGame(first, second);
        } catch (IllegalPlayerConfiguration e) {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body(new ErrorDTO(e.getLocalizedMessage()));
        }

        return ResponseEntity.ok(new NewGameDTO(newGame));
    }

    @GetMapping("/status")
    public ResponseEntity<?> getGameStatus(@RequestParam("gameId") int gameId) {
        Game game;
        try {
            game = gameService.getGameById(gameId);
        } catch (IllegalGameId e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO(e.getLocalizedMessage()));
        }

        return ResponseEntity.ok(new GameDTO(game));
    }

    @GetMapping("/play")
    public ResponseEntity<?> playMove(@RequestParam("gameId") int gameId,
                                      @RequestParam("row") int row,
                                      @RequestParam("column") int column) {
        try {
            gameService.playMove(gameId, row, column);
        } catch (IllegalGameId e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO(e.getLocalizedMessage()));
        } catch (IllegalMove e) {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body(new ErrorDTO(e.getLocalizedMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
