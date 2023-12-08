package nymil.chess.logic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nymil.chess.logic.exeptions.ChessExeption;

import java.util.Objects;

public class ChessGame {
    public static int NEXT_GAME_ID = 1;
    private final int gameId;
    private final Player playerWhite;
    private Player playerBlack;
    private final ChessBoard board = new ChessBoard();
    private boolean started = false;
    private Player winner = null;

    @JsonCreator
    public ChessGame(
            @JsonProperty("userName") String name
    ) {
        this.playerWhite = new Player(name);
        this.gameId = NEXT_GAME_ID++;
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public int getId() {
        return gameId;
    }

    public boolean hasStarted() {
        return started;
    }

    public String getName() {
        return String.format("%s's game", playerWhite.getUserName());
    }

    public void joinGame(Player joiningPlayer) {
        if (Objects.equals(joiningPlayer.getUserName(), playerWhite.getUserName())) {
            throw new ChessExeption("Cant join a game of player with the same name");
        }

        if (this.started) {
            throw new ChessExeption("Cant join a game that has already started");
        }

        // this should never happen since the game needs to have started to have a winner but just to be sure
        if (!Objects.isNull(winner)) {
            throw new ChessExeption("Cant join a game that is already over");
        }

        this.playerBlack = joiningPlayer;
        this.started = true;
    }
}
