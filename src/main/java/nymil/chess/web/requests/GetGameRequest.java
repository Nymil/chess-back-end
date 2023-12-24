package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.ChessGame;
import nymil.chess.logic.domain.chesspieces.ChessPiece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameRequest extends Request {
    public static final String SPEC_GAME_ID = "gameId";
    public static final String SPEC_UUID = "uuid";
    private JsonObject returnObject = new JsonObject();
    public GetGameRequest(RoutingContext ctx) {
        super(ctx);
    }

    public void setGameData(ChessGame game) {
        returnObject.put("gameId", game.getId())
                .put("started", game.hasStarted())
                .put("currentPlayer", game.getCurrentPlayer())
                .put("playerWhite", game.getPlayerWhite())
                .put("playerBlack", game.getPlayerBlack())
                .put("winner", game.getWinner())
                .put("board", game.getBoard().getState())
                .put("capturedPieces", game.getCapturedPieces())
                .put("history", game.getHistory());
    }

    public int getGameId() {
        return params.pathParameter(SPEC_GAME_ID).getInteger();
    }

    public String getUuid() {
        return params.headerParameter(SPEC_UUID).getString();
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, returnObject);
    }
}
