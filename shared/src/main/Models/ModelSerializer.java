package Models;

import Adapters.boardAdapter;
import Adapters.gameAdapter;
import Adapters.pieceAdapter;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import com.google.gson.*;

import java.io.Reader;
import java.io.StringReader;

public class ModelSerializer {
    public static <T> T deserialize(String json, Class<T> responseClass) {
        return deserialize(new StringReader(json), responseClass);
    }

    public static <T> T deserialize(Reader reader, Class<T> responseClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessGame.class, new gameAdapter());
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new boardAdapter());
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new pieceAdapter());
        return gsonBuilder.create().fromJson(reader, responseClass);
    }
}
