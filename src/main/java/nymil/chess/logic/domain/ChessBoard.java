package nymil.chess.logic.domain;

import io.vertx.codegen.annotations.Nullable;
import nymil.chess.logic.domain.chesspieces.*;
import nymil.chess.logic.exeptions.ChessExeption;

import java.util.*;

public class ChessBoard {
    private final Map<BoardLocation, ChessPiece> state;

    public ChessBoard() {
        this.state = getStartingState();
    }

    public Map<BoardLocation, ChessPiece> getState() {
        return state;
    }

    @Nullable
    public BoardLocation getLocation(ChessPiece piece) {
        for (Map.Entry<BoardLocation, ChessPiece> entry : this.state.entrySet()) {
            if (piece.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Map<BoardLocation, ChessPiece> getStartingState() {
        Map<BoardLocation, ChessPiece> startState = new HashMap<>();

        // placing pawns
        for (char colLetter : BoardLocation.possibleLetters) {
            startState.put(new BoardLocation(String.format("%c2", colLetter)), new Pawn("white", this));
            startState.put(new BoardLocation(String.format("%c7", colLetter)), new Pawn("black", this));
        }

        // rooks
        for (char colLetter : new char[]{'a', 'h'}) {
            startState.put(new BoardLocation(String.format("%c1", colLetter)), new Rook("white", this));
            startState.put(new BoardLocation(String.format("%c8", colLetter)), new Rook("black", this));
        }

        // knights
        for (char colLetter : new char[]{'b', 'g'}) {
            startState.put(new BoardLocation(String.format("%c1", colLetter)), new Knight("white", this));
            startState.put(new BoardLocation(String.format("%c8", colLetter)), new Knight("black", this));
        }

        // bishops
        for (char colLetter : new char[]{'c', 'f'}) {
            startState.put(new BoardLocation(String.format("%c1", colLetter)), new Bishop("white", this));
            startState.put(new BoardLocation(String.format("%c8", colLetter)), new Bishop("black", this));
        }

        // queens
        startState.put(new BoardLocation("d1"), new Queen("white", this));
        startState.put(new BoardLocation("d8"), new Queen("black", this));

        // kings
        startState.put(new BoardLocation("e1"), new King("white", this));
        startState.put(new BoardLocation("e8"), new King("black", this));

        return startState;
    }

    public Set<Move> getPossibleMoves(BoardLocation location) {
        if (!state.containsKey(location)) {
            throw new ChessExeption("No chess piece at location " + location.toString());
        }

        return state.get(location).getPossibleMoves(state);
    }
}
