package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;

import java.util.*;

public class Knight extends ChessPiece {

    public Knight(String color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public Set<Move> getPossibleMoves() {
        Set<Move> possibleMoves = new HashSet<>();

        BoardLocation startingLocation = board.getLocation(this);

        for (Map<String, Integer> coordDif : getPossibleMoveCoordDifs()) {
            int possibleCol = startingLocation.getCol() + coordDif.get("colDif");
            int possibleRow = startingLocation.getRow() + coordDif.get("rowDif");

            if (!BoardLocation.inRange(possibleCol, possibleRow)) continue;
            BoardLocation possibleLocation = new BoardLocation(possibleCol, possibleRow);

            if (board.hasPieceOfColor(possibleLocation, this.color)) continue;
            possibleMoves.add(new Move(startingLocation, possibleLocation));
        }

        return possibleMoves;
    }

    @Override
    public String toString() {
        return color + " knight";
    }

    public List<Map<String, Integer>> getPossibleMoveCoordDifs() {
        return Arrays.asList(
                new HashMap<>() {{
                    put("colDif", 1);
                    put("rowDif", 2);
                }},
                new HashMap<>() {{
                    put("colDif", 2);
                    put("rowDif", 1);
                }},
                new HashMap<>() {{
                    put("colDif", 2);
                    put("rowDif", -1);
                }},
                new HashMap<>() {{
                    put("colDif", 1);
                    put("rowDif", -2);
                }},
                new HashMap<>() {{
                    put("colDif", -1);
                    put("rowDif", -2);
                }},
                new HashMap<>() {{
                    put("colDif", -2);
                    put("rowDif", -1);
                }},
                new HashMap<>() {{
                    put("colDif", -2);
                    put("rowDif", 1);
                }},
                new HashMap<>() {{
                    put("colDif", -1);
                    put("rowDif", 2);
                }}
        );
    }
}
