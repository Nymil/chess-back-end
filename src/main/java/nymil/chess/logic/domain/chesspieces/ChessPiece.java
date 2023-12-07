package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.Move;

import java.util.Map;
import java.util.Set;

public abstract class ChessPiece {
    private final ChessPieceColor color;

    public ChessPiece(String color) {
        this.color = ChessPieceColor.fromString(color);
    }

    public abstract Set<Move> getPossibleMoves(Map<ChessPiece, BoardLocation> currentBoardState);
}
