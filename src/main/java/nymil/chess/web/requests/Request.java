package nymil.chess.web.requests;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;

public abstract class Request {
    final RoutingContext ctx;
    final RequestParameters params;

    Request(RoutingContext ctx) {
        this.ctx = ctx;
        this.params = ctx.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    }

    public abstract void sendResponse();
}
