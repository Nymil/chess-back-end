package nymil.chess.logic.domain;

import nymil.chess.logic.domain.chesspieces.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChessBoard {
    private final Map<BoardLocation, ChessPiece> state;

    public ChessBoard() {
        this.state = getStartingState();
    }

    public Map<BoardLocation, ChessPiece> getState() {
        return state;
    }

    private Map<BoardLocation, ChessPiece> getStartingState() {
        Map<BoardLocation, ChessPiece> startState = new HashMap<>();

        // placing pawns
        for (char colLetter : BoardLocation.possibleLetters) {
            startState.put(new BoardLocation(String.format("%c2", colLetter)), new Pawn("white"));
            startState.put(new BoardLocation(String.format("%c7", colLetter)), new Pawn("black"));
        }

        // rooks
        for (char colLetter : new char[]{'a', 'h'}) {
            startState.put(new BoardLocation(String.format("%c1", colLetter)), new Rook("white"));
            startState.put(new BoardLocation(String.format("%c8", colLetter)), new Rook("black"));
        }

        // knights
        for (char colLetter : new char[]{'b', 'g'}) {
            startState.put(new BoardLocation(String.format("%c1", colLetter)), new Knight("white"));
            startState.put(new BoardLocation(String.format("%c8", colLetter)), new Knight("black"));
        }

        // bishops
        for (char colLetter : new char[]{'c', 'f'}) {
            startState.put(new BoardLocation(String.format("%c1", colLetter)), new Bishop("white"));
            startState.put(new BoardLocation(String.format("%c8", colLetter)), new Bishop("black"));
        }

        // queens
        startState.put(new BoardLocation("d1"), new Queen("white"));
        startState.put(new BoardLocation("d8"), new Queen("black"));

        // kings
        startState.put(new BoardLocation("e1"), new King("white"));
        startState.put(new BoardLocation("e8"), new King("black"));

        return startState;
    }
}
