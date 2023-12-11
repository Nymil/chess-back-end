package nymil.chess.logic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nymil.chess.logic.exeptions.ChessExeption;
import nymil.chess.logic.util.toStringSerializer;

import java.util.Arrays;
import java.util.Objects;

@JsonSerialize(using = toStringSerializer.class)
public class BoardLocation {
    private final int col; // zero based
    private final int row; // zero based
    private final String locationString;
    public final static char[] possibleLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    @JsonCreator
    public BoardLocation(
            @JsonProperty("location") String locationString
    ) {
        if (!validLocationString(locationString)) {
            throw new ChessExeption("invalid location");
        }

        this.col = colFromLocationString(locationString);
        this.row = rowFromLocationString(locationString);
        this.locationString = locationString;
    }

    public BoardLocation(int col, int row) {
        if (col < 0 || col > 7 || row < 0 || row > 7) {
            throw new ChessExeption("invalid location");
        }

        this.col = col;
        this.row = row;

        this.locationString = coordsToString(col, row);
    }

    private String coordsToString(int col, int row) {
        return String.format("%c%d", possibleLetters[col], row + 1);
    }

    private int colFromLocationString(String locationString) {
        return Arrays.binarySearch(possibleLetters, locationString.charAt(0));
    }

    private int rowFromLocationString(String locationString) {
        return Integer.parseInt(locationString.substring(1)) - 1;
    }

    private boolean validLocationString(String locationString) {
        if (locationString.length() != 2) {
            return false;
        }

        char letter = locationString.charAt(0);
        if (Arrays.binarySearch(possibleLetters, letter) < 0) {
            return false;
        }

        int number = Integer.parseInt(locationString.substring(1));
        return number >= 1 && number <= 8;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return locationString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardLocation that = (BoardLocation) o;
        return col == that.col && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }
}
