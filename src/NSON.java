package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import src.exception.JSONFileException;
import src.exception.JSONParseException;
import src.utility.SymbolBalancer;

/**
 * NSON (Nested Serialization Object Notation)
 * --------------------------------------------
 * A lightweight JSON-like data handler that mimics Python's `json` module
 * with additional flexibility and custom parsing logic. This class supports
 * methods to load, parse, dump, and validate JSON data using internal
 * utilities.
 * 
 * Main Goals:
 * - Minimal external dependencies
 * - Custom parsing logic with nested structure support
 * - Syntax validation before parsing
 * 
 * Designed for educational and lightweight JSON processing use cases.
 */
public class NSON implements JSON {

    /**
     * Loads and parses a JSON file from the given file path.
     * Equivalent to Python’s {@code json.load()}.
     *
     * @param file_object The file object pointing to a JSON file.
     * @return A Map representing the JSON structure.
     * @throws JSONFileException  If the file is missing or unreadable.
     * @throws JSONParseException If the file content is malformed.
     */
    public Map<String, Object> load(File file_object) {
        if (file_object == null || !file_object.exists() || !file_object.isFile()) {
            throw new JSONFileException("Invalid file path or file does not exist: " + file_object);
        }

        try {
            // Read all content from the file
            String content = Files.readString(file_object.toPath());

            // Delegate parsing to the `loads` method
            return loads(content);

        } catch (IOException e) {
            throw new JSONFileException("Failed to read file: " + file_object.getPath());
        } catch (JSONParseException e) {
            throw e; // Already handled by `loads`
        } catch (Exception e) {
            throw new JSONFileException("Unexpected error while loading JSON from file: " + e.getMessage());
        }
    }

    /**
     * Parses a raw JSON string into a Map.
     * Equivalent to Python’s {@code json.loads()}.
     *
     * @param jsonStr A string containing raw JSON.
     * @return A Map representation of the JSON object.
     * @throws JSONParseException If the input string is malformed.
     */
    @Override
    public Map<String, Object> loads(String jsonStr) {
        try {
            return RawJSONHandler.parseMap(jsonStr);
        } catch (Exception e) {
            throw new JSONParseException("Failed to parse JSON string.");
        }
    }

    /**
     * Serializes a Map object and writes it to a file as JSON.
     * Equivalent to Python’s {@code json.dump()}.
     *
     * @param data        The Map to be serialized.
     * @param file_object The file to write the JSON output to.
     * @param indent      Indentation level (0 = compact, >0 = pretty print).
     * @throws JSONFileException If file writing fails.
     */
    @Override
    public void dump(Map<String, Object> data, File file_object, int indent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Converts a Map into a JSON-formatted string.
     * Equivalent to Python’s {@code json.dumps()}.
     *
     * @param data   The Map to convert to JSON.
     * @param indent Indentation level for formatting (0 = compact).
     * @param sort   Whether to sort keys alphabetically.
     * @return A JSON string representing the input map.
     */
    @Override
    public String dumps(Map<String, Object> data, int indent, boolean sort) {
        return toJsonString(data, indent, 0);
    }

    /**
     * Converts a Java Map object (representing a JSON structure) into a valid JSON
     * string.
     * <p>
     * This method performs serialization of complex nested structures such as:
     * <ul>
     * <li>Primitive key-value pairs</li>
     * <li>Nested objects (Map within Map)</li>
     * <li>Arrays (Lists of any supported type)</li>
     * </ul>
     *
     * It supports pretty-printing by using indentation for nested elements. The
     * result is
     * a well-formatted, human-readable JSON string. For compact output, pass
     * <code>indent = 0</code>.
     *
     * <h3>Examples:</h3>
     * Given input:
     * 
     * <pre>{@code
     * Map<String, Object> data = new HashMap<>();
     * data.put("name", "Nikhil");
     * data.put("age", 20);
     * data.put("languages", List.of("Java", "Python"));
     * }</pre>
     *
     * Calling <code>dumps(data, 4, false)</code> will output:
     * 
     * <pre>
     * {
     *     "name": "Nikhil",
     *     "age": 20,
     *     "languages": [
     *         "Java",
     *         "Python"
     *     ]
     * }
     * </pre>
     *
     * @param data   The Map representing the JSON structure to serialize.
     * @param indent Number of spaces used per indentation level. If 0, returns
     *               compact JSON.
     * @param sort   (Currently ignored) When implemented, will sort keys
     *               alphabetically if true.
     * @return A string containing valid JSON representation of the input data.
     * @throws NullPointerException if the input map is null.
     */

    private String toJsonString(Object obj, int indent, int level) {
        String pad = " ".repeat(indent * level);
        String padNext = " ".repeat(indent * (level + 1));

        if (obj instanceof Map<?, ?> map) {
            StringBuilder sb = new StringBuilder("{\n");
            List<String> entries = new ArrayList<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                String key = "\"" + entry.getKey() + "\"";
                String value = toJsonString(entry.getValue(), indent, level + 1);
                entries.add(padNext + key + ": " + value);
            }
            sb.append(String.join(",\n", entries));
            sb.append("\n").append(pad).append("}");
            return sb.toString();

        } else if (obj instanceof List<?> list) {
            StringBuilder sb = new StringBuilder("[\n");
            List<String> items = new ArrayList<>();
            for (Object item : list) {
                items.add(padNext + toJsonString(item, indent, level + 1));
            }
            sb.append(String.join(",\n", items));
            sb.append("\n").append(pad).append("]");
            return sb.toString();

        } else if (obj instanceof String str) {
            return "\"" + str.replace("\"", "\\\"") + "\"";

        } else {
            return String.valueOf(obj); // number, boolean, null
        }
    }

    /**
     * Validates whether the provided string is syntactically correct JSON.
     * Uses bracket and quote balancing checks via {@link SymbolBalancer}.
     *
     * @param jsonStr The JSON string to validate.
     * @return {@code true} if valid, {@code false} otherwise.
     */
    @Override
    public boolean isValidJSON(String jsonStr) {
        if (jsonStr == null || jsonStr.isBlank())
            return false;
        return SymbolBalancer.isFullyBalanced(jsonStr);
    }
}
