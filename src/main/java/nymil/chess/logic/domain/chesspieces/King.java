package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;

import java.util.*;

public class King extends ChessPiece {
    public King(String color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public Set<Move> getPossibleMoves() {
        List<Map<String, Integer>> possibleMoveCoordDifs = getPossibleMoveCoordDifs();
        return new HashSet<>(getMovesFromPossibleMoveCoordDifList(possibleMoveCoordDifs));
    }

    @Override
    public String toString() {
        return color + " king";
    }

    public List<Map<String, Integer>> getPossibleMoveCoordDifs() {
        return Arrays.asList(
                new HashMap<>() {{
                    put("colDif", 0);
                    put("rowDif", 1);
                }},
                new HashMap<>() {{
                    put("colDif", 1);
                    put("rowDif", 1);
                }},
                new HashMap<>() {{
                    put("colDif", 1);
                    put("rowDif", 0);
                }},
                new HashMap<>() {{
                    put("colDif", 1);
                    put("rowDif", -1);
                }},
                new HashMap<>() {{
                    put("colDif", 0);
                    put("rowDif", -1);
                }},
                new HashMap<>() {{
                    put("colDif", -1);
                    put("rowDif", -1);
                }},
                new HashMap<>() {{
                    put("colDif", -1);
                    put("rowDif", 0);
                }},
                new HashMap<>() {{
                    put("colDif", -1);
                    put("rowDif", 1);
                }}
        );
    }
}
