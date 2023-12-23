package nymil.chess.logic.domain.chesspieces;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessBoard;
import nymil.chess.logic.domain.Move;
import nymil.chess.logic.util.toStringSerializer;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    public abstract Set<Move> getPossibleMoves();
    public abstract String toString();
    public ChessPieceColor getColor() {
        return color;
    }

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
