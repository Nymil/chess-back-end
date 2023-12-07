package nymil.chess.logic.util;


import java.util.UUID;

public class Uuid4 { // wrapper for the UUID java util class for simplification
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
