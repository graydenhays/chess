package Services;

/**
 * Represents a response to a login request to a chess game. Contains an authToken, a username, and potentially an error message.
 */
public class LoginResponse {
    private String message;
    private String authToken;
    private String username;

    /**
     * Creates an instance of the LoginResponse class
     */
    public LoginResponse() {  }

    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
