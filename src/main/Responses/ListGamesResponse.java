package Responses;

import Models.GameModel;
import chess.Game;

import java.util.Collection;

/**
 * Represents a response to a request for a list of all games. Contains an array of games, an authentication token, and an error message.
 */
public class ListGamesResponse {
    private int status = 200;
    /**
     * Authorization token for the response
     */
    private String authToken;
    /**
     * GameModel instance for the response
     */
    private Collection<GameModel> games;
    /**
     * Potential error message for the response
     */
    private String message;

    /**
     * Creates an instance of the ListGamesResponse class
     */
    public ListGamesResponse()  {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuthorization() {
        return authToken;
    }

    public void setAuthorization(String authToken) {
        this.authToken = authToken;
    }

    public Collection<GameModel> getGames() {
        return games;
    }

    public void setGames(Collection<GameModel> gameList) {
        this.games = gameList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
