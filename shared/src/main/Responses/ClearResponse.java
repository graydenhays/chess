package Responses;

/**
 * Represents a response to a request to clear a database
 */
public class ClearResponse {
    /**
     * Potential error message for response
     */
    public String message;

    /**
     * Creates an instance of the ClearResponse class
     */
    public ClearResponse(){

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
