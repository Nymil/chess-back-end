package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.Move;

import java.util.Map;
import java.util.Set;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public Set<Move> getPossibleMoves(Map<BoardLocation, ChessPiece> currentBoardState) {
        return null;
    }

    @Override
    public String toString() {
        return color + " bishop";
    }
}
