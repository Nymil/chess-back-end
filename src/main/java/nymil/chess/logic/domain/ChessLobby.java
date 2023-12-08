package nymil.chess.logic.domain;

import nymil.chess.logic.exeptions.ChessExeption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ChessLobby {
    List<ChessGame> games = new ArrayList<>();

    public void addGame(ChessGame gameToAdd) {
        games.add(gameToAdd);
    }

    public ChessGame getGameById(int gameId) {
        ChessGame gameWithId = games.stream()
                .filter(chessGame -> chessGame.getId() == gameId)
                .findFirst()
                .orElse(null);

        if (Objects.isNull(gameWithId)) {
            throw new ChessExeption("No game with id: " + gameId);
        }

        return gameWithId;
    }
}
