package Responses;

import java.util.UUID;

/**
 * Creates a response to a RegisterRequest. Contains a username, password, and authToken
 */
public class RegisterResponse {
    private int status = 200;
    /**
     * Potential error message for response
     */
    private String message;
    /**
     * String representation of an authentication token for request
     */
    private String authToken;
    /**
     * String representation of a username for the request
     */
    private String username;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Creates an instance of the RegisterResponse class
     */


    public RegisterResponse()   {
        authToken = UUID.randomUUID().toString();
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
