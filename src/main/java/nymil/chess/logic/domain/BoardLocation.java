package nymil.chess.logic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

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
            throw new IllegalArgumentException("invalid location");
        }

        this.col = colFromLocationString(locationString);
        this.row = rowFromLocationString(locationString);
        this.locationString = locationString;
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
