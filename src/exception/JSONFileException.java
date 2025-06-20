package src.exception;
// package exception;

/**
 * Thrown when there's a file I/O error during JSON processing.
 */
public class JSONFileException extends JSONException {
    public JSONFileException(String message) {
        super("JSON File Error: " + message);
    }
}
