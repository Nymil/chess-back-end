package nymil.chess.logic.domain;

public class Move {
    private final BoardLocation origin;
    private final BoardLocation destination;
    public Move(BoardLocation origin, BoardLocation destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public BoardLocation getOrigin() {
        return origin;
    }

    public BoardLocation getDestination() {
        return destination;
    }
}
