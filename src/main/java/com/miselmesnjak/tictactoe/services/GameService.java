package com.miselmesnjak.tictactoe.services;

import com.miselmesnjak.tictactoe.domain.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Misel on 5.2.2017.
 */
@Service
@Data
public class GameService {
    private final List<Game> gameList = new ArrayList<>();
    private final Set<Player> players = new TreeSet<>();

    public Game createNewGame(String first, String second) throws IllegalPlayerConfiguration {
        if (isIllegalPlayerConfiguration(first, second)) {
            throw new IllegalPlayerConfiguration();
        }

        Player firstPlayer = new Player(first, CellValue.X);
        players.add(firstPlayer);
        Player secondPlayer = new Player(second, CellValue.O);
        players.add(secondPlayer);

        Game game = new Game(gameList.size() + 1, firstPlayer, secondPlayer);
        gameList.add(game);

        return game;
    }

    private void checkMoveLegality(int row, int column, Game game) throws IllegalMove {
        if (row < 1 || row > PlayBoard.LENGTH || column < 0 || column > PlayBoard.LENGTH) {
            throw new IllegalMove("Row or Column is out of boundary!");
        } else if (game.getBoard().getCells()[row - 1][column - 1] != null) {
            throw new IllegalMove(String.format("Cell on row %d and column %d already has a value!", row, column));
        } else if (game.getStatus().equals(GameStatus.finished)) {
            throw new IllegalMove("Can't play a move on finished game!");
        }
    }

    public void playMove(int gameId, int row, int column) throws IllegalMove, IllegalGameId {
        Game game = getGameById(gameId);
        checkMoveLegality(row, column, game);

        game.getBoard().getCells()[row - 1][column - 1] = new Cell(game.getCurrentPlayer().getName(), game.getCurrentPlayer().getMark());
        game.incrementMoves();
        game.switchPlayer();

        if (!checkGameStatus(game).equals(GameStatus.finished)) {
            playAIMove();
        }
    }

    private void playAIMove() {

    }

    private GameStatus checkGameStatus(Game game) {
        if (game.getMoves() == game.MAX_MOVES) {
            game.setStatus(GameStatus.finished);
        }

        //TODO: check if either player won

        return game.getStatus();
    }

    private boolean isIllegalPlayerConfiguration(String first, String second) {
        return !(Player.COMPUTER.equals(first) || Player.COMPUTER.equals(second)) ||
                (Player.COMPUTER.equals(first) && Player.COMPUTER.equals(second));
    }

    public Game getGameById(int gameId) throws IllegalGameId {
        if (gameList.size() < gameId || gameId < 1) {
            throw new IllegalGameId(gameId);
        }

        return gameList.get(gameId - 1);
    }
}
