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
import java.util.Objects;
import java.util.Set;

public class Pawn extends ChessPiece {
    private int direction;
    public Pawn(String color, ChessBoard board) {
        super(color, board);
        this.direction = this.color == ChessPieceColor.WHITE ? 1 : -1;
    }

    @Override
    public Set<Move> getPossibleMoves() { // TODO: moves resulting in check
        Set<Move> possibleMoves = new HashSet<>();

        int steps = hasMoved ? 1 : 2;
        BoardLocation currentLocation = board.getLocation(this);

        int startRow = currentLocation.getCol();
        int startCol = currentLocation.getRow();

        // straight moves
        for (int distance = 1; distance <= steps; distance++) {
            int possibleRow = startRow + direction * distance;
            if (!BoardLocation.inRange(startCol, possibleRow)) break;

            BoardLocation possibleLocation = new BoardLocation(startCol, possibleRow);
            if (!board.hasPiece(possibleLocation)) break;

            possibleMoves.add(new Move(currentLocation, possibleLocation));
        }

        // diagonal taking moves
        int possibleRow = startRow + direction;
        int[] possibleCols = new int[] {startCol - 1, startCol + 1};
        for (int possibleCol : possibleCols) {
            if (!BoardLocation.inRange(possibleCol, possibleRow)) break;

            BoardLocation possibleLocation = new BoardLocation(possibleCol, possibleRow);
            if (!board.hasPieceOfColor(possibleLocation, enemyColor)) break;

            possibleMoves.add(new Move(currentLocation, possibleLocation));
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return color + " pawn";
    }
}
