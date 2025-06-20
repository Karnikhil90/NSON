package src.exception;
// package exception;
/**
 * Base exception for all JSON-related issues.
 */
public class JSONException extends RuntimeException {
    public JSONException(String message) {
        super(message);
    }
}
