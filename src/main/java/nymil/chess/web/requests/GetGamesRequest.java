package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nymil.chess.logic.domain.ChessGame;

import java.util.List;

public class GetGamesRequest extends Request {

    private JsonObject returnObject = new JsonObject();

    public GetGamesRequest(RoutingContext ctx) {
        super(ctx);
    }

    public void setGames(List<ChessGame> games) {
        returnObject.put("games", games.stream().map(game -> {
            return new JsonObject().put("gameId", game.getId())
                                    .put("name", game.getName())
                                    .put("started", game.hasStarted());
        }).toList());
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, returnObject);
    }
}
