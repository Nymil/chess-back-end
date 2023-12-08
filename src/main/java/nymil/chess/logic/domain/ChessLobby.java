package nymil.chess.logic.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChessLobby {
    List<ChessGame> games = new ArrayList<>();

    public String addGame(ChessGame gameToAdd) {
        games.add(gameToAdd);
        return gameToAdd.getPlayerWhite().getUuid();
    }
}
