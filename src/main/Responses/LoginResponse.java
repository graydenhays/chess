package Responses;

/**
 * Represents a response to a login request to a chess game. Contains an authToken, a username, and potentially an error message.
 */
public class LoginResponse {
    /**
     * Potential error message for response
     */
    private String message;
    /**
     * String representation for authentication token for response
     */
    private String authToken;
    /**
     * String representation for response's username
     */
    private String username;

    /**
     * Creates an instance of the LoginResponse class
     */

    private int status = 200;
    public LoginResponse() {  }

    public String getUsername() {
        return username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
