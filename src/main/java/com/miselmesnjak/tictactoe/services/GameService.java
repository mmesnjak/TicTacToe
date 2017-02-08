package com.miselmesnjak.tictactoe.services;

import com.miselmesnjak.tictactoe.domain.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Misel on 5.2.2017.
 */
@Service
@Data
public class GameService {
    private final List<Game> gameList = new ArrayList<>();
    private final Map<String, Player> players = new HashMap<>();

    public Game createNewGame(String first, String second) throws IllegalPlayerConfiguration {
        if (isIllegalPlayerConfiguration(first, second)) {
            throw new IllegalPlayerConfiguration();
        }

        Player firstPlayer;
        firstPlayer = players.containsKey(first) ? players.get(first) : new Player(first);
        firstPlayer.setMark(CellValue.X);
        players.put(first, firstPlayer);

        Player secondPlayer;
        secondPlayer = players.containsKey(second) ? players.get(second) : new Player(second);
        secondPlayer.setMark(CellValue.O);
        players.put(second, secondPlayer);

        int gameId = gameList.size() + 1;
        Game game = new Game(gameId, firstPlayer, secondPlayer);

        gameList.add(chooseGameStrategy(game));

        if (firstPlayer.isComputer()) {
            playAIMove(gameId);
        }

        return game;
    }

    private Game chooseGameStrategy(Game game) {
        Player humanPlayer = game.getFirstPlayer().isComputer() ? game.getSecondPlayer() : game.getFirstPlayer();

        game.setAiGameStrategy(GameStrategyFactory.create(humanPlayer));

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

        game.getBoard().getCells()[row - 1][column - 1] = new Cell(game.getCurrentPlayer().getName(), game.getCurrentPlayer().getMark(), row - 1, column - 1);
        game.incrementMoves();
        game.switchPlayer();

        if (!checkGameStatus(game).equals(GameStatus.finished)) {
            playAIMove(gameId);
        }
    }

    private void playAIMove(int gameId) {

    }

    private GameStatus checkGameStatus(Game game) {
        if (game.getMoves() == game.MAX_MOVES) {
            game.setStatus(GameStatus.finished);
        }

        //TODO: check if either player won

        return game.getStatus();
    }

    private boolean isIllegalPlayerConfiguration(String first, String second) {
        return !(Player.COMPUTER.equalsIgnoreCase(first) || Player.COMPUTER.equalsIgnoreCase(second)) ||
                (Player.COMPUTER.equalsIgnoreCase(first) && Player.COMPUTER.equalsIgnoreCase(second));
    }

    public Game getGameById(int gameId) throws IllegalGameId {
        if (gameList.size() < gameId || gameId < 1) {
            throw new IllegalGameId(gameId);
        }

        return gameList.get(gameId - 1);
    }
}
