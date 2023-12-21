package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;

import java.util.Map;
import java.util.Set;

public class Queen extends ChessPiece {

    public Queen(String color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public Set<Move> getPossibleMoves() {
        return null;
    }

    @Override
    public String toString() {
        return color + " queen";
    }
}
