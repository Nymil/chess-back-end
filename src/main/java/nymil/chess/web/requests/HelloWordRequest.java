package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class HelloWordRequest extends Request{
    JsonObject returnObject = new JsonObject().put("response", "Hello World!");
    public HelloWordRequest(RoutingContext ctx) {
        super(ctx);
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, returnObject);
    }
}
