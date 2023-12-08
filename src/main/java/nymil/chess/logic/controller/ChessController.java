package nymil.chess.logic.controller;

import nymil.chess.logic.domain.ChessGame;
import nymil.chess.logic.domain.Player;

public interface ChessController {
    void addGame(ChessGame gameToAdd);
    void joinGame(int gameId, Player joiningPlayer);
    ChessGame getGameById(int gameId);
}
