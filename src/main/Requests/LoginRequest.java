package Requests;


/**
 * Represents a login request. Contains a username and password.
 */
public class LoginRequest {
    /**
     * String representation for request's username
     */
    private String username;
    /**
     * String representation for request's password
     */
    private String password;

    /**
     * Creates a request to login for a specific user
     */
    public LoginRequest() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
