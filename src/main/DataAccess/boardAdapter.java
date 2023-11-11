package DataAccess;

import chess.Board;
import chess.ChessPiece;
import chess.Piece;
import com.google.gson.*;

import java.lang.reflect.Type;

public class boardAdapter implements JsonDeserializer<Board> {
        public Board deserialize(JsonElement el, Type type, JsonDeserializationContext ctx) throws JsonParseException {
            var builder = new GsonBuilder();
            builder.registerTypeAdapter(ChessPiece.class, new pieceAdapter());
            return builder.create().fromJson(el, Board.class);
        }
}
