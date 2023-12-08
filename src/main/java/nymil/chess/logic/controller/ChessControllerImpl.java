package nymil.chess.logic.controller;

import nymil.chess.logic.domain.ChessGame;
import nymil.chess.logic.domain.ChessLobby;

public class ChessControllerImpl implements ChessController {
    ChessLobby lobby = new ChessLobby();
    @Override
    public String addGame(ChessGame gameToAdd) {
        return lobby.addGame(gameToAdd);
    }
}
