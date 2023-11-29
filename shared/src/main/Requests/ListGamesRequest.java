package Requests;

import Models.AuthToken;

/**
 * Represents a request to get a list of all games
 */
public class ListGamesRequest {
    /**
     * Authentication token for the request
     */
    private String authorization;

    /**
     * Creates an instance of the ListGamesRequest class
     */
    public ListGamesRequest()   {

    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
