package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;

import java.util.Map;
import java.util.Set;

public class Knight extends ChessPiece {

    public Knight(String color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public Set<Move> getPossibleMoves(Map<BoardLocation, ChessPiece> currentBoardState) {
        return null;
    }

    @Override
    public String toString() {
        return color + " knight";
    }
}
