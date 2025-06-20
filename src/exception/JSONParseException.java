package src.exception;
// package exception;

/**
 * Thrown when the JSON string cannot be parsed.
 */
public class JSONParseException extends JSONException {
    public JSONParseException(String message) {
        super("JSON Parse Error: " + message);
    }
}
