package Services;

import Models.User;

/**
 * Represents a request to register a user. Contains a username, email, and password.
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    /**
     * Creates a request to register a specific user
     *
     * @param user
     */
    public RegisterRequest(User user)    {

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
