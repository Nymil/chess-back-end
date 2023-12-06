package nymil.chess.web;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.RouterBuilder;
import nymil.chess.web.bridge.ChessOpenApiBridge;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer extends AbstractVerticle {
    private static Logger LOGGER = Logger.getLogger(WebServer.class.getName());
    private Promise<Void> startPromise;
    private final ChessOpenApiBridge openApiBridge;

    public WebServer(ChessOpenApiBridge openApiBridge) {
        this.openApiBridge = openApiBridge;
    }

    public WebServer() {
        this(new ChessOpenApiBridge());
    }

    @Override
    public void start(Promise<Void> startPromise) {
        this.startPromise = startPromise;
        ConfigRetriever.create(vertx).getConfig()
                .onFailure(cause -> shutDown("Failed to load configuration", cause))
                .onSuccess(configuration -> {
                    LOGGER.log(Level.INFO, "Configuration loaded: {0}", configuration);
                    start(startPromise, configuration);
                });
    }

    private void start(Promise<Void> startPromise, JsonObject configuration) {
        RouterBuilder.create(vertx, configuration.getJsonObject("api").getString("url"))
                .onFailure(cause -> shutDown("Failed to load API specification", cause))
                .onSuccess(routerBuilder -> {
                    LOGGER.log(Level.INFO, "API specification loaded: {0}",
                            routerBuilder.getOpenAPI().getOpenAPI().getJsonObject("info").getString("version"));
                    start(startPromise, configuration, routerBuilder);
                });
    }

    private void start(Promise<Void> startPromise, JsonObject configuration, RouterBuilder routerBuilder) {
        Router mainRouter = Router.router(vertx);

        mainRouter.route("/*")
                .subRouter(openApiBridge.buildRouter(routerBuilder));

        final int port = configuration.getJsonObject("http").getInteger("port");
        LOGGER.log(Level.INFO, "Server will be listening at port {0}", port);

        vertx.createHttpServer()
                .requestHandler(mainRouter)
                .listen(port)
                .onFailure(cause -> shutDown("Failed to start server", cause))
                .onSuccess(server -> {
                    LOGGER.log(Level.INFO, "Server is listening on port: {0}", server.actualPort());
                    LOGGER.log(Level.INFO, "Handler: {0}", server.requestHandler());
                    startPromise.complete();
                });
    }

    private void shutDown(String message, Throwable cause) {
        LOGGER.log(Level.SEVERE, message, cause);
        LOGGER.info("Shutting down");
        vertx.close();
        startPromise.fail(cause);
    }
}
