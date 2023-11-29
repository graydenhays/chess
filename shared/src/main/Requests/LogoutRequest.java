package Requests;

import Models.AuthToken;

/**
 * Represents a request to logout a specific user
 */
public class LogoutRequest {
    /**
     * Authentication token for request
     */
    private String authorization;

    /**
     * Creates an instance of the LogoutRequest class
     */
    public LogoutRequest()  {

    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
