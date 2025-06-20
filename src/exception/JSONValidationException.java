package src.exception;
// package exception;

/**
 * Thrown when the JSON is syntactically invalid or fails validation.
 */
public class JSONValidationException extends JSONException {
    public JSONValidationException(String message) {
        super("JSON Validation Error: " + message);
    }
}
