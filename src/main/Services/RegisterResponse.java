package Services;

/**
 * Creates a response to a RegisterRequest. Contains a username, password, and authToken
 */
public class RegisterResponse {
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

    /**
     * Creates an instance of the RegisterResponse class
     */
    public RegisterResponse()   {

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
