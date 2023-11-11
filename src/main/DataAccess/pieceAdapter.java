package DataAccess;

import chess.Piece;
import com.google.gson.*;

import java.lang.reflect.Type;

public class pieceAdapter implements JsonDeserializer<Piece> {
    public Piece deserialize(JsonElement el, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        return new Gson().fromJson(el, Piece.class);
    }
}