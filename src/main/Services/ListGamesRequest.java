package Services;

import Models.AuthToken;

/**
 * Represents a request to get a list of all games
 */
public class ListGamesRequest {
    private AuthToken authorization;

    /**
     * Creates an instance of the ListGamesRequest class
     */
    public ListGamesRequest()   {

    }

    public AuthToken getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthToken authorization) {
        this.authorization = authorization;
    }
}
