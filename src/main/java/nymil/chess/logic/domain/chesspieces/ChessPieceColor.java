package nymil.chess.logic.domain.chesspieces;

public enum ChessPieceColor {
    WHITE("white"),
    BLACK("black");

    private final String color;

    ChessPieceColor(String color) {this.color = color;}

    public static ChessPieceColor fromString(String type) {
        for(ChessPieceColor chessPieceColor: ChessPieceColor.values()){
            if (chessPieceColor.color.equals(type)) {
                return chessPieceColor;
            }
        }

        throw new IllegalArgumentException("No such chess piece color");
    }

    @Override
    public String toString() {
        return this.color;
    }
}
