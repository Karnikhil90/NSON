package src;
// package your.package.name; // Replace with actual package

import java.io.File;
import java.util.Map;

/**
 * JSON Interface for handling basic JSON serialization and deserialization.
 * Inspired by Python's built-in `json` module, this defines methods for:
 * loading, dumping, and validating JSON data.
 */
public interface JSON {

    /**
     * Loads JSON from a File object and parses it into a Map.
     * Equivalent to Python's json.load()
     *
     * @param fileObject A File pointing to a JSON file.
     * @return Parsed JSON as a Map
     */
    Map<String, Object> load(File fileObject);

    /**
     * Parses a raw JSON string into a Map.
     * Equivalent to Python's json.loads()
     *
     * @param jsonStr Raw JSON string
     * @return Parsed JSON as a Map
     */
    Map<String, Object> loads(String jsonStr);

    /**
     * Writes a Map object to a file as JSON.
     * Equivalent to Python's json.dump()
     *
     * @param data       Map to write
     * @param fileObject File to write the JSON into
     * @param indent     Indentation level (0 = compact)
     */
    void dump(Map<String, Object> data, File fileObject, int indent);

    /**
     * Converts a Map to a JSON string.
     * Equivalent to Python's json.dumps()
     *
     * @param data   Map to convert
     * @param indent Indentation level (0 = compact)
     * @param sort   Whether to sort keys alphabetically
     * @return JSON string
     */
    String dumps(Map<String, Object> data, int indent, boolean sort);

    /**
     * Checks if the provided string is structurally valid JSON.
     *
     * @param jsonStr The JSON string to validate
     * @return true if valid, false otherwise
     */
    boolean isValidJSON(String jsonStr);
}
