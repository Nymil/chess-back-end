package nymil.chess.logic.domain.chesspieces;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.Move;
import nymil.chess.logic.util.toStringSerializer;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@JsonSerialize(using = toStringSerializer.class)
public abstract class ChessPiece {
    protected final ChessPieceColor color;
    protected boolean hasMoved = false;

    public ChessPiece(String color) {
        this.color = ChessPieceColor.fromString(color);
    }

    public abstract Set<Move> getPossibleMoves(Map<BoardLocation, ChessPiece> currentBoardState);
    public abstract String toString();

    public void setMoved() {
        this.hasMoved = true;
    }

    public BoardLocation findOwnLocation(Map<BoardLocation, ChessPiece> boardState) {
        Optional<Map.Entry<BoardLocation, ChessPiece>> entry = boardState.entrySet()
                .stream()
                .filter(e -> this.equals(e.getValue()))
                .findFirst();

        return entry.map(Map.Entry::getKey).orElse(null);
    }
}
