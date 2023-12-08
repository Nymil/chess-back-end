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
                .put("currentPlayer", Objects.isNull(game.getCurrentPlayer()) ? null : game.getCurrentPlayer().getUserName())
                .put("playerWhite", game.getPlayerWhite().getUserName())
                .put("playerBlack", Objects.isNull(game.getPlayerBlack()) ? null : game.getPlayerBlack().getUserName())
                .put("board", toReadableMap(game.getBoard().getState()))
                .put("capturedPieces", game.getCapturedPieces());
    }

    public int getGameId() {
        return params.pathParameter(SPEC_GAME_ID).getInteger();
    }

    private Map<BoardLocation, String> toReadableMap(Map<BoardLocation, ChessPiece> boardState) {
        Map<BoardLocation, String> readableMap = new HashMap<>();

        for (Map.Entry<BoardLocation, ChessPiece> entry : boardState.entrySet()) {
            readableMap.put(entry.getKey(), entry.getValue().toString());
        }

        return readableMap;
    }

    public String getUuid() {
        return params.headerParameter(SPEC_UUID).getString();
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, returnObject);
    }
}
