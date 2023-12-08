package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nymil.chess.logic.domain.ChessGame;
import nymil.chess.logic.domain.Player;
import nymil.chess.web.exceptions.InvalidRequestException;

public class JoinGameRequest extends Request{
    private static final String SPEC_GAME_ID = "gameId";
    private JsonObject returnObject = new JsonObject();
    public JoinGameRequest(RoutingContext ctx) {
        super(ctx);
    }

    public Player getPlayer() {
        try {
            return params.body().getJsonObject().mapTo(Player.class);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Unable to decipher the data in the request body");
        }
    }

    public int getGameId() {
        return params.pathParameter(SPEC_GAME_ID).getInteger();
    }

    public void setUuid(String uuid) {
        returnObject.put("uuid", uuid);
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 201, returnObject);
    }
}
