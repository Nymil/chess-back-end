package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class CreateGameRequest extends Request{

    JsonObject returnObject = new JsonObject();

    public CreateGameRequest(RoutingContext ctx) {
        super(ctx);
    }
    public void setUuid(String uuid) {
        returnObject.put("uuid", uuid);
    }
    public String getUserName() {
        return params.body().getJsonObject().getString("userName");
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 201, returnObject);
    }
}
