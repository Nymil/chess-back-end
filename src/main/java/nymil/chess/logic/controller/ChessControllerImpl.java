package nymil.chess.logic.controller;

import nymil.chess.logic.domain.*;

import java.util.List;
import java.util.Set;

public class ChessControllerImpl implements ChessController {
    ChessLobby lobby = new ChessLobby();
    @Override
    public void addGame(ChessGame gameToAdd) {
        lobby.addGame(gameToAdd);
    }

    @Override
    public void joinGame(int gameId, Player player) {
        ChessGame gameWithId = getGameById(gameId);
        gameWithId.joinGame(player);
    }

    @Override
    public ChessGame getGameById(int gameId) {
        return lobby.getGameById(gameId);
    }

    @Override
    public List<ChessGame> getGames() {
        return lobby.getGames();
    }

    @Override
    public Set<Move> getMoves(BoardLocation location, ChessGame game) {
        return null;
    }
}
