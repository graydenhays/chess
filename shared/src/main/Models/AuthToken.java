package Models;

/**
 * Creates an authentication token
 */
public class AuthToken {
    /**
     * String representing the authentication token
     */
    public String authToken;
    /**
     * String representing the username related to the authentication token
     */
    public String username;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
