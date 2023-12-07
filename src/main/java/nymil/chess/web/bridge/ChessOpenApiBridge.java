package nymil.chess.web.bridge;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.Operation;
import io.vertx.ext.web.openapi.RouterBuilder;
import nymil.chess.web.exceptions.InvalidRequestException;
import nymil.chess.web.requests.HelloWordRequest;
import nymil.chess.web.requests.Response;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessOpenApiBridge {
    private static final Logger LOGGER = Logger.getLogger(ChessOpenApiBridge.class.getName());
    public Router buildRouter(RouterBuilder routerBuilder) {
        LOGGER.log(Level.INFO, "Installing cors handlers");
        routerBuilder.rootHandler(createCorsHandler());

        LOGGER.log(Level.INFO, "Setting up failure handlers");
        routerBuilder.operations().forEach(operation -> operation.failureHandler(this::onFailedRequest));

        LOGGER.log(Level.INFO, "Setting up handler for: helloWorld");
        routerBuilder.operation("helloWorld")
                .handler(ctx -> sendHelloWorld(new HelloWordRequest(ctx)));

        LOGGER.log(Level.INFO, "All handlers are installed, creating router.");
        return routerBuilder.createRouter();
    }

    private void sendHelloWorld(HelloWordRequest request) {
        request.sendResponse();
    }

    private void onFailedRequest(RoutingContext ctx) {
        Throwable cause = ctx.failure();
        int code = ctx.statusCode();
        String message = Objects.isNull(cause) ? "" + code : cause.getMessage();

        // custom exception mapping
        LOGGER.log(Level.INFO, "Failed request", cause);
        if (cause instanceof IllegalArgumentException) {
            code = 400;
        } else if (cause instanceof InvalidRequestException) {
            code = 400;
        } else {
            LOGGER.log(Level.WARNING, "Failed request", cause);
        }

        Response.sendFailure(ctx, code, message);
    }

    private CorsHandler createCorsHandler() {
        return CorsHandler.create(".*.")
                .allowedHeader("x-requested-with")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .allowedHeader("origin")
                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
                .allowedHeader("accept")
                .allowedMethod(HttpMethod.HEAD)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PUT);
    }
}