package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nymil.chess.logic.domain.ChessGame;

public class CreateGameRequest extends Request{

    JsonObject returnObject = new JsonObject();

    public CreateGameRequest(RoutingContext ctx) {
        super(ctx);
    }
    public void setUuid(String uuid) {
        returnObject.put("uuid", uuid);
    }
    public ChessGame getCreatedGame() {
        return params.body().getJsonObject().mapTo(ChessGame.class);
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 201, returnObject);
    }
}
