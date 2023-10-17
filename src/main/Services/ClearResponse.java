package Services;

/**
 * Represents a response to a request to clear a database
 */
public class ClearResponse {
    public String message;

    /**
     * Creates an instance of the ClearResponse class
     */
    ClearResponse(){

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
