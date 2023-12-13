package nymil.chess.web.requests;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import nymil.chess.logic.domain.BoardLocation;
import nymil.chess.logic.domain.Move;
import nymil.chess.web.exceptions.InvalidRequestException;

import java.util.Set;

public class GetMovesRequest extends Request{
    public static final String SPEC_GAME_ID = "gameId";
    public static final String SPEC_UUID = "uuid";
    private JsonObject returnObject = new JsonObject();
    public GetMovesRequest(RoutingContext ctx) {
        super(ctx);
    }

    public BoardLocation getLocation() {
        try {
            return params.body().getJsonObject().mapTo(BoardLocation.class);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Unable to decipher the data in the request body");
        }
    }

    public int getGameId() {
        return params.pathParameter(SPEC_GAME_ID).getInteger();
    }

    public String getUuid() {
        return params.headerParameter(SPEC_UUID).getString();
    }

    public void setPossibleMoves(Set<Move> possibleMoves) {
        returnObject.put("possibleMoves", possibleMoves.stream().toList());
    }

    @Override
    public void sendResponse() {
        Response.sendJson(ctx, 200, returnObject);
    }
}
