package nymil.chess.web.bridge;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.RouterBuilder;
import nymil.chess.logic.controller.ChessController;
import nymil.chess.logic.controller.ChessControllerImpl;
import nymil.chess.logic.domain.*;
import nymil.chess.logic.exeptions.ChessExeption;
import nymil.chess.web.exceptions.InvalidRequestException;
import nymil.chess.web.requests.*;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessOpenApiBridge {
    private static final Logger LOGGER = Logger.getLogger(ChessOpenApiBridge.class.getName());
    private final ChessController controller;
    public ChessOpenApiBridge(ChessController controller) {
        this.controller = controller;
    }
    public ChessOpenApiBridge() {
        this(new ChessControllerImpl());
    }
    public Router buildRouter(RouterBuilder routerBuilder) {
        LOGGER.log(Level.INFO, "Installing cors handlers");
        routerBuilder.rootHandler(createCorsHandler());

        LOGGER.log(Level.INFO, "Setting up failure handlers");
        routerBuilder.operations().forEach(operation -> operation.failureHandler(this::onFailedRequest));

        LOGGER.log(Level.INFO, "Setting up handler for: helloWorld");
        routerBuilder.operation("helloWorld")
                .handler(ctx -> sendHelloWorld(new HelloWordRequest(ctx)));

        LOGGER.log(Level.INFO, "Setting up handler for: createGame");
        routerBuilder.operation("createGame")
                .handler(ctx -> createGame(new CreateGameRequest(ctx)));

        LOGGER.log(Level.INFO, "Setting up handler for: joinGame");
        routerBuilder.operation("joinGame")
                .handler(ctx -> joinGame(new JoinGameRequest(ctx)));

        LOGGER.log(Level.INFO, "Setting up handler for: getGames");
        routerBuilder.operation("getGames")
                .handler(ctx -> getGames(new GetGamesRequest(ctx)));

        LOGGER.log(Level.INFO, "Setting up handler for: getGame");
        routerBuilder.operation("getGame")
                .handler(ctx -> getGame(new GetGameRequest(ctx)));

        LOGGER.log(Level.INFO, "Setting up handler for: getMoves");
        routerBuilder.operation("getMoves")
                .handler(ctx -> getMoves(new GetMovesRequest(ctx)));

        LOGGER.log(Level.INFO, "All handlers are installed, creating router.");
        return routerBuilder.createRouter();
    }

    private void sendHelloWorld(HelloWordRequest request) {
        request.sendResponse();
    }

    private void getMoves(GetMovesRequest request) {
        ChessGame game = controller.getGameById(request.getGameId());

        if (!hasAccessToDetailsOfGame(game, request.getUuid())) {
            throw new IllegalArgumentException("You dont have access to the game details as you are not a part of the game");
        }

        BoardLocation requestLocation = request.getLocation();
        Set<Move> possibleMoves = controller.getMoves(requestLocation, game);
        request.setPossibleMoves(possibleMoves);
        request.sendResponse();
    }

    private void getGames(GetGamesRequest request) {
        request.setGames(controller.getGames());
        request.sendResponse();
    }

    private void getGame(GetGameRequest request) {
        ChessGame game = controller.getGameById(request.getGameId());

        if (!hasAccessToDetailsOfGame(game, request.getUuid())) {
            throw new IllegalArgumentException("You dont have access to the game details as you are not a part of the game");
        }

        request.setGameData(game);
        request.sendResponse();
    }

    private boolean hasAccessToDetailsOfGame(ChessGame game, String uuid) {
        return Objects.equals(game.getPlayerWhite().getUuid(), uuid) || (!Objects.isNull(game.getPlayerBlack()) && Objects.equals(game.getPlayerBlack().getUuid(), uuid));
    }

    private void createGame(CreateGameRequest request) {
        ChessGame game = request.getCreatedGame();
        controller.addGame(game);
        request.setUuid(game.getPlayerWhite().getUuid());
        request.sendResponse();
    }

    private void joinGame(JoinGameRequest request) {
        Player createdPlayer = request.getPlayer();
        int gameId = request.getGameId();
        controller.joinGame(gameId, createdPlayer);
        request.setUuid(createdPlayer.getUuid());
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
        } else if (cause instanceof IllegalStateException) {
            code = 400;
        } else if (cause instanceof InvalidRequestException) {
            code = 400;
        } else if (cause instanceof ChessExeption) {
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
