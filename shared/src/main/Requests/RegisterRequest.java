package Requests;

/**
 * Represents a request to register a user. Contains a username, email, and password.
 */
public class RegisterRequest {
    /**
     * String representation of username for request
     */
    private String username;
    /**
     * String representation of password for request
     */
    private String password;
    /**
     * String representation of email for request
     */
    private String email;

    /**
     * Creates a request to register a specific user
     */
    public RegisterRequest()    {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
