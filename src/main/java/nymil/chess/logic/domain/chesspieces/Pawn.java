package nymil.chess.logic.domain.chesspieces;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.Converter;
import io.vertx.core.json.JsonObject;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pawn extends ChessPiece {
    private int direction;
    public Pawn(String color, ChessBoard board) {
        super(color, board);
        this.direction = this.color == ChessPieceColor.WHITE ? 1 : -1;
    }

    @Override
    public Set<Move> getPossibleMoves() {
        Set<Move> possibleMoves = new HashSet<>();

        int steps = this.hasMoved ? 1 : 2;


        return possibleMoves;
    }

    @Override
    public String toString() {
        return color + " pawn";
    }
}
