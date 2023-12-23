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
    public Set<Move> getPossibleMoves() { // TODO: remove moves resulting in check
        Set<Move> possibleMoves = new HashSet<>();

        int steps = hasMoved ? 1 : 2;
        BoardLocation startLocation = board.getLocation(this);

        int startCol = startLocation.getCol();
        int startRow = startLocation.getRow();

        // straight moves
        for (int walkedDistance = 1; walkedDistance <= steps; walkedDistance++) {
            int possibleRow = startRow + direction * walkedDistance;
            if (!BoardLocation.inRange(startCol, possibleRow)) break;

            BoardLocation possibleLocation = new BoardLocation(startCol, possibleRow);
            if (board.hasPiece(possibleLocation)) break;

            possibleMoves.add(new Move(startLocation, possibleLocation));
        }

        // diagonal taking moves
        int possibleRow = startRow + direction;
        int[] possibleCols = new int[] {startCol - 1, startCol + 1};
        for (int possibleCol : possibleCols) {
            if (!BoardLocation.inRange(possibleCol, possibleRow)) break;

            BoardLocation possibleLocation = new BoardLocation(possibleCol, possibleRow);
            // can only take if location has enemy piece
            if (!board.hasPieceOfColor(possibleLocation, enemyColor)) break;

            possibleMoves.add(new Move(startLocation, possibleLocation));
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return color + " pawn";
    }
}
