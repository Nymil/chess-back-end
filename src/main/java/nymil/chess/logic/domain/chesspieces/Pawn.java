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

        BoardLocation startingLocation = findOwnLocation(currentBoardState);
        int startingCol = startingLocation.getCol();
        int startingRow = startingLocation.getRow();
        int rowDif = color == ChessPieceColor.WHITE ? 1 : -1;

        // straight moves
        int stepCount = hasMoved ? 1 : 2;
        for (int step = 1; step < 1 + stepCount; step++) {
            int newRow = startingRow + step * rowDif;
            BoardLocation newLocation = new BoardLocation(startingCol, newRow);

            if (currentBoardState.containsKey(newLocation)) { // a piece is already there
                break;
            }
            possibleMoves.add(new Move(startingLocation, newLocation));
        }

        // diagonal taking moves
        BoardLocation leftDiagonal = new BoardLocation(startingCol - 1, startingRow + rowDif);
        BoardLocation rightDiagonal = new BoardLocation(startingCol + 1, startingRow + rowDif);

        if (currentBoardState.containsKey(leftDiagonal) && currentBoardState.get(leftDiagonal).color != this.color) {
            possibleMoves.add(new Move(startingLocation, leftDiagonal));
        }
        if (currentBoardState.containsKey(rightDiagonal) && currentBoardState.get(rightDiagonal).color != this.color) {
            possibleMoves.add(new Move(startingLocation, rightDiagonal));
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return color + " pawn";
    }
}
