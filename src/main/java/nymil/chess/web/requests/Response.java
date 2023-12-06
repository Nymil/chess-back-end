package nymil.chess.web.requests;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class Response {
    public Response() {/* Utility class */}

    public static void sendJson(RoutingContext ctx, int statusCode, Object response) {
        ctx.response()
                .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setStatusCode(statusCode)
                .end(Json.encodePrettily(response));
    }

    public static void sendFailure(RoutingContext ctx, int code, String message) {
        sendJson(ctx, code, new JsonObject()
                .put("failure", code)
                .put("cause", message));
    }
}
