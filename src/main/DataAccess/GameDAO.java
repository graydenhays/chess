package DataAccess;

import Adapters.boardAdapter;
import Adapters.pieceAdapter;
import Models.GameModel;
import chess.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataAccess.DataAccessException;
import dataAccess.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Data Access Object (DAO) class for managing games in the database.
 * This class provides methods for inserting, retrieving, updating, and finding GameModel objects.
 */
public class GameDAO {
    /**
     * A static HashMap class variable for storing games
     */

    public static Database db = new Database();

    /**
     * Inserts new game into the database.
     *
     * @param game The GameModel object to insert.
     */

    public int Insert(GameModel game) throws SQLException, DataAccessException {

        Connection conn = db.getConnection();

        String whiteUsername = game.getWhiteUsername();
        String blackUsername = game.getBlackUsername();
        String gameName = game.getGameName();
        ChessGame chessGame = game.getGame();
        if (whiteUsername == null || whiteUsername.matches("[a-zA-Z]+") && (blackUsername == null || blackUsername.matches("[a-zA-Z]+")) && (gameName == null || gameName.matches("[a-zA-Z]+"))) {
            var statement = "INSERT INTO gameDAO (whiteUsername, blackUsername, gameName, game) VALUES(?, ?, ?, ?)";
            try (var preparedStatement = conn.prepareStatement(statement)) {
                preparedStatement.setString(1, whiteUsername);
                preparedStatement.setString(2, blackUsername);
                preparedStatement.setString(3, gameName);
                preparedStatement.setString(4, new Gson().toJson(chessGame));
                preparedStatement.executeUpdate();
            }
        }
        else {
            throw new DataAccessException("Error: bad input");
        }
        if(game.getGameID() > 0)   {
            UpdateGame(game.getGameID(), game);
            return game.getGameID();
        }
        else {
            int gameID = 0;
            try (var preparedStatement = conn.prepareStatement("SELECT gameID FROM gameDAO ORDER BY gameID DESC LIMIT 1")) {
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        gameID = rs.getInt("gameID");
                    }
                }
            }
            db.returnConnection(conn);
            return gameID;
        }
    }

    /**
     * Retrieves a specified game from the database by gameID
     *
     * @param gameID The unique identifier of the game to find.
     * @return The found GameModel object, or null if not found.
     */
    public GameModel Find(Integer gameID) throws SQLException, DataAccessException {

        Connection conn = db.getConnection();

        GameModel gameModel = new GameModel();
        gameModel.setGameID(gameID);
        String game = null;
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM gameDAO WHERE gameID=?")) {
            preparedStatement.setString(1, Integer.toString(gameID));
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    game = rs.getString("game");
                    gameModel.setGameName(rs.getString("gameName"));
                    gameModel.setWhiteUsername(rs.getString("whiteUsername"));
                    gameModel.setBlackUsername(rs.getString("blackUsername"));
                }
            }
        }

        if(game == null && gameModel.getGameName() == null && gameModel.getBlackUsername() == null && gameModel.getWhiteUsername() == null)    {
            throw new DataAccessException("Error: Could not find game");
        }

        db.returnConnection(conn);
        var builder = new GsonBuilder();
        builder.registerTypeAdapter(ChessBoard.class, new boardAdapter());
        builder.registerTypeAdapter(ChessPiece.class, new pieceAdapter());
        var tempGame = builder.create().fromJson(game, Game.class);
        gameModel.setGame(tempGame);

        return gameModel;
    }

    /**
     * Retrieves all games from the database
     *
     * @return A collection of GameModel objects, or null if none are found.
     */

    public Collection<GameModel> FindAll() throws DataAccessException, SQLException   {
        Connection conn = db.getConnection();

        HashSet<GameModel> games = new HashSet<GameModel>();

        try (var preparedStatement = conn.prepareStatement("SELECT * FROM gameDAO")) {
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    GameModel gameModel = new GameModel();
                    gameModel.setGameName(rs.getString("gameName"));
                    gameModel.setWhiteUsername(rs.getString("whiteUsername"));
                    gameModel.setBlackUsername(rs.getString("blackUsername"));
                    gameModel.setGameID(rs.getInt("gameID"));

                    var json = rs.getString("game");
                    var builder = new GsonBuilder();
                    builder.registerTypeAdapter(ChessBoard.class, new boardAdapter());
                    builder.registerTypeAdapter(ChessPiece.class, new pieceAdapter());
                    var game = builder.create().fromJson(json, Game.class);
                    gameModel.setGame(game);

                    games.add(gameModel);
                }
            }
        }
        db.returnConnection(conn);
        return games;
    }

    /**
     * Updates game in the database. Replaces the chessGame string that corresponds to a given gameID with a new chessGame string.
     *
     * @param gameID The unique identifier of the game to update.
     * @param game The game to be updated
     */

    public void UpdateGame(Integer gameID, GameModel game) throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        try (var preparedStatement = conn.prepareStatement("UPDATE gameDAO SET gameName=?, whiteUsername=?, blackUsername=?, game=? WHERE gameID=?")) {
            preparedStatement.setString(1, game.getGameName());
            preparedStatement.setString(2, game.getWhiteUsername());
            preparedStatement.setString(3, game.getBlackUsername());
            preparedStatement.setString(4, new Gson().toJson(game));
            preparedStatement.setInt(5, gameID);

            preparedStatement.executeUpdate();
        }
        db.returnConnection(conn);
    }

    /**
     * Claims a spot in the game. Saves a player's username as a white or black player.
     *
     * @param username
     * @param game
     * @param playerColor
     */
    public void ClaimSpot(String username, GameModel game, String playerColor) throws DataAccessException, SQLException {
        if(game == null)    {
            game = new GameModel();
            int gameID = Insert(game);
            game.setGameID(gameID);
        }
        if(Objects.equals(playerColor, "WHITE") || Objects.equals(playerColor, "white"))  {
            if(game.getWhiteUsername() == null) {
                game.setWhiteUsername(username);
            }
            else {
                throw new DataAccessException("Error: spot already taken");
            }
        }
        else if(Objects.equals(playerColor, "BLACK") || Objects.equals(playerColor, "black")){
            if(game.getBlackUsername() == null) {
                game.setBlackUsername(username);
            }
            else {
                throw new DataAccessException("Error: spot already taken");
            }
        }
        UpdateGame(game.getGameID(), game);
    }

    /**
     * Clears all Game data from the database
     */

    public void Clear() throws SQLException, DataAccessException {
        Connection conn = db.getConnection();

        try (var preparedStatement = conn.prepareStatement("TRUNCATE gameDAO")) {
            preparedStatement.executeUpdate();
        }

        db.returnConnection(conn);
    }
}
