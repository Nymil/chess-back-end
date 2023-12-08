package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nymil.chess.logic.domain.ChessGame;
import nymil.chess.web.exceptions.InvalidRequestException;

import java.util.logging.Level;

public class CreateGameRequest extends Request{
    JsonObject returnObject = new JsonObject();

    public CreateGameRequest(RoutingContext ctx) {
        super(ctx);
    }
    public void setUuid(String uuid) {
        returnObject.put("uuid", uuid);
    }
    public ChessGame getCreatedGame() {
        try {
            return params.body().getJsonObject().mapTo(ChessGame.class);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Unable to decipher the data in the request body");
        }
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 201, returnObject);
    }
}
