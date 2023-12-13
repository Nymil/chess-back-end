package nymil.chess.logic.controller;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessGame;
import nymil.chess.logic.domain.Move;
import nymil.chess.logic.domain.Player;

import java.util.List;
import java.util.Set;

public interface ChessController {
    void addGame(ChessGame gameToAdd);
    void joinGame(int gameId, Player joiningPlayer);
    ChessGame getGameById(int gameId);
    List<ChessGame> getGames();
    Set<Move> getMoves(BoardLocation location, ChessGame game);
}
