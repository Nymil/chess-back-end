package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.Move;

import java.util.Map;
import java.util.Set;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public Set<Move> getPossibleMoves(Map<ChessPiece, BoardLocation> currentBoardState) {
        return null;
    }

    @Override
    public String toString() {
        return color + " pawn";
    }
}
