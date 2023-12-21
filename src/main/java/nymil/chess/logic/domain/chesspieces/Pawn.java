package nymil.chess.logic.domain.chesspieces;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.Converter;
import io.vertx.core.json.JsonObject;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.Move;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public Set<Move> getPossibleMoves(Map<BoardLocation, ChessPiece> currentBoardState) { // TODO: implement en passant
        Set<Move> possibleMoves = new HashSet<>();



        return possibleMoves;
    }

    @Override
    public String toString() {
        return color + " pawn";
    }
}
