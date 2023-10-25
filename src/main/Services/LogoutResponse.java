package Services;

import Models.AuthToken;

/**
 * Represents a response to a logout request for a chess game. Contains an authToken and potentially an error message.
 */
public class LogoutResponse {
    /**
     * Potential error message for response
     */
    private String message;
    /**
     * Authentication token for the response
     */
    private AuthToken authorization;

    /**
     * Creates an instance of the LogoutResponse class
     */
    public LogoutResponse() {

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthToken getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthToken authorization) {
        this.authorization = authorization;
    }
}
