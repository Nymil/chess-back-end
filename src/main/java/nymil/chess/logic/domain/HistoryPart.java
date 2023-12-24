package nymil.chess.logic.domain;

import nymil.chess.logic.domain.chesspieces.ChessPiece;

import java.util.Map;

public class HistoryPart {
    private final ChessPiece capturedPiece;
    private final Player player;
    private final Move move;
    public HistoryPart(ChessPiece capturedPiece, Player player, Move move) {
        this.capturedPiece = capturedPiece;
        this.player = player;
        this.move = move;
    }

    public ChessPiece getCapturedPiece() {
        return capturedPiece;
    }

    public Player getPlayer() {
        return player;
    }

    public Move getMove() {
        return move;
    }
}
