package nymil.chess.logic.domain;

import nymil.chess.logic.domain.chesspieces.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChessBoard {
    private final Map<ChessPiece, BoardLocation> state;

    public ChessBoard() {
        this.state = getStartingState();
    }

    private Map<ChessPiece, BoardLocation> getStartingState() {
        Map<ChessPiece, BoardLocation> startState = new HashMap<>();

        // placing pawns
        for (char colLetter : BoardLocation.possibleLetters) {
            startState.put(new Pawn("white"), new BoardLocation(String.format("%c2", colLetter)));
            startState.put(new Pawn("black"), new BoardLocation(String.format("%c7", colLetter)));
        }

        // rooks
        for (char colLetter : new char[]{'a', 'h'}) {
            startState.put(new Rook("white"), new BoardLocation(String.format("%c1", colLetter)));
            startState.put(new Rook("black"), new BoardLocation(String.format("%c8", colLetter)));
        }

        // knights
        for (char colLetter : new char[]{'b', 'g'}) {
            startState.put(new Knight("white"), new BoardLocation(String.format("%c1", colLetter)));
            startState.put(new Knight("black"), new BoardLocation(String.format("%c8", colLetter)));
        }

        // bishops
        for (char colLetter : new char[]{'c', 'f'}) {
            startState.put(new Bishop("white"), new BoardLocation(String.format("%c1", colLetter)));
            startState.put(new Bishop("black"), new BoardLocation(String.format("%c8", colLetter)));
        }

        // queens
        startState.put(new Queen("white"), new BoardLocation("d1"));
        startState.put(new Queen("black"), new BoardLocation("d8"));

        // kings
        startState.put(new King("white"), new BoardLocation("e1"));
        startState.put(new King("black"), new BoardLocation("e8"));

        return startState;
    }
}
