package Models;

/**
 * Creates a model User
 */
public class User {
    /**
     * String username for user instance
     */
    public String username;
    /**
     * String password for user instance
     */
    public String password;
    /**
     * String email for user instance
     */
    public String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
