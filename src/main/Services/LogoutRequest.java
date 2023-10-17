package Services;

import Models.AuthToken;

/**
 * Represents a request to logout a specific user
 */
public class LogoutRequest {
    private AuthToken authorization;

    /**
     * Creates an instance of the LogoutRequest class
     */
    public LogoutRequest()  {

    }

    public AuthToken getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthToken authorization) {
        this.authorization = authorization;
    }
}
