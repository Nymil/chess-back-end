package nymil.chess.logic.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nymil.chess.logic.util.Uuid4;

public class Player {
    private final String uuid;
    private final String userName;

    @JsonCreator
    public Player(
            @JsonProperty("userName") String userName
    ) {
        this.userName = userName;
        this.uuid = Uuid4.generateUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public String getUserName() {
        return userName;
    }

}
