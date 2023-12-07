package nymil.chess.logic.domain;

import nymil.chess.logic.domain.chesspieces.ChessPiece;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChessBoard {
    private final Map<ChessPiece, BoardLocation> state;

    public ChessBoard() {
        this.state = getStartingState();
    }

    private Map<ChessPiece, BoardLocation> getStartingState() {
        Map<ChessPiece, BoardLocation> startState = new HashMap<>();



        return startState;
    }
}
