package nymil.chess.logic.domain.chesspieces;

import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;
import org.jcodings.util.Hash;

import java.util.*;
import java.util.stream.Stream;

public class Queen extends ChessPiece {

    public Queen(String color, ChessBoard board) {
        super(color, board);
    }

    @Override
    public Set<Move> getPossibleMoves() {
        List<Map<String, Integer>> directions = getDirections();
        return new HashSet<>(getStraightLineMoves(directions));
    }

    @Override
    public String toString() {
        return color + " queen";
    }

    public List<Map<String, Integer>> getDirections() {
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
