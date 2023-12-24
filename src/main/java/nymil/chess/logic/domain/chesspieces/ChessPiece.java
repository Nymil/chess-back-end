package nymil.chess.logic.domain.chesspieces;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;
import nymil.chess.logic.util.toStringSerializer;

import java.util.*;

@JsonSerialize(using = toStringSerializer.class)
public abstract class ChessPiece {
    protected final ChessPieceColor color;
    protected final ChessPieceColor enemyColor;
    protected boolean hasMoved = false;
    protected ChessBoard board;

    public ChessPiece(String color, ChessBoard board) {
        this.color = ChessPieceColor.fromString(color);
        this.enemyColor = Objects.equals(color, "black") ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
        this.board = board; // every piece is part of a board
    }

    public Set<Move> getStraightLineMoves(List<Map<String, Integer>> directions) { // TODO: moves resulting in a check
        Set<Move> straightLineMoves = new HashSet<>();

        BoardLocation startingLocation = board.getLocation(this);

        for (Map<String, Integer> direction : directions) {
            int colDif = direction.get("colDif");
            int rowDif = direction.get("rowDif");

            int currentCol = startingLocation.getCol() + colDif;
            int currentRow = startingLocation.getRow() + rowDif;

            while (true) {
                if (!BoardLocation.inRange(currentCol, currentRow)) break;

                BoardLocation currentLocation = new BoardLocation(currentCol, currentRow);

                if (board.hasPieceOfColor(currentLocation, this.color)) break;
                straightLineMoves.add(new Move(startingLocation, currentLocation));

                if (board.hasPieceOfColor(currentLocation, enemyColor)) break;
                currentCol += colDif;
                currentRow += rowDif;
            }
        }

        return straightLineMoves;
    }

    public Set<Move> getMovesFromPossibleMoveCoordDifList(List<Map<String, Integer>> possibleMoveCoordDifs) {
        Set<Move> possibleMoves = new HashSet<>();

        BoardLocation startingLocation = board.getLocation(this);

        for (Map<String, Integer> coordDif : possibleMoveCoordDifs) {
            int possibleCol = startingLocation.getCol() + coordDif.get("colDif");
            int possibleRow = startingLocation.getRow() + coordDif.get("rowDif");

            if (!BoardLocation.inRange(possibleCol, possibleRow)) continue;
            BoardLocation possibleLocation = new BoardLocation(possibleCol, possibleRow);

            if (board.hasPieceOfColor(possibleLocation, this.color)) continue;
            possibleMoves.add(new Move(startingLocation, possibleLocation));
        }

        return possibleMoves;
    }

    public abstract Set<Move> getPossibleMoves();
    public abstract String toString();
    public ChessPieceColor getColor() {
        return color;
    }

    public void setMoved() {
        this.hasMoved = true;
    }
}
