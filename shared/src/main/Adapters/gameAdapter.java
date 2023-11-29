package Adapters;

import chess.ChessBoard;
import com.google.gson.*;
import chess.Game;
import java.lang.reflect.Type;

public class gameAdapter implements JsonDeserializer<Game> {
    public Game deserialize(JsonElement el, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(ChessBoard.class, new boardAdapter());
        return builder.create().fromJson(el, Game.class);
    }
}
