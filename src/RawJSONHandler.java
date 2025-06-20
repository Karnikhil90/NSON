package src;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.utility.NSONUtils;

public class RawJSONHandler extends NSONUtils {

    // ------------------------------------------------------------------------
    // 2. ------------------- Flat Key-Value Parsers --------------------------
    // ------------------------------------------------------------------------

    public static Map<String, String> get_single_map_object(String str) {
        Map<String, String> map = new HashMap<>();
        if (str == null || str.isEmpty()) return map;

        String[] pair = str.split(":", 2);
        if (pair.length == 2) {
            map.put(get_data_between_quotes(pair[0].trim()), get_data_between_quotes(pair[1].trim()));
        }
        return map;
    }

    public static List<Map<String, String>> get_multiple_map_object(String str) {
        List<Map<String, String>> list = new ArrayList<>();
        if (str == null || str.isEmpty()) return list;

        String[] pairs = str.split(",");
        for (String pair : pairs) {
            Map<String, String> singleMap = get_single_map_object(pair.trim());
            if (!singleMap.isEmpty()) {
                list.add(singleMap);
            }
        }
        return list;
    }

    public static Map<String, String> get_multiple_map_object_(String str) {
        Map<String, String> map = new HashMap<>();
        if (str == null || str.isEmpty()) return map;

        String[] pairs = str.split(",");
        for (String pair : pairs) {
            Map<String, String> singleMap = get_single_map_object(pair.trim());
            map.putAll(singleMap);
        }
        return map;
    }

    public static List<Map<String, String>> get_full_map(String str) {
        return get_multiple_map_object(get_data_between_curly_bracket(str));
    }

    public static Map<String, String> get_full_map_(String str) {
        return get_multiple_map_object_(get_data_between_curly_bracket(str));
    }

    public static List<Map<String, String>> get_full_maps(String str) {
        List<Map<String, String>> list = new ArrayList<>();
        if (str == null || str.isEmpty()) return list;

        int start = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{') {
                start = i;
            } else if (str.charAt(i) == '}' && start != -1) {
                String block = str.substring(start + 1, i).trim();
                List<Map<String, String>> maps = get_multiple_map_object(block);
                list.addAll(maps);
                start = -1;
            }
        }
        return list;
    }

    // ------------------------------------------------------------------------
    // 3. ---------------- Recursive Value & Map Parsing ----------------------
    // ------------------------------------------------------------------------

    public static Object parseValue(String str) {
        str = str.trim();
        if (str.startsWith("{") && str.endsWith("}")) {
            return parseMap(str);
        } else if (str.startsWith("[") && str.endsWith("]")) {
            return parseList_(str);
        } else {
            return get_data_between_quotes(str);
        }
    }

    public static Map<String, Object> parseMap(String str) {
        str = get_data_between(str, '{', '}');
        Map<String, Object> map = new HashMap<>();
        List<String> pairs = splitTopLevel(str, ',');

        for (String pair : pairs) {
            String[] kv = pair.split(":", 2);
            if (kv.length == 2) {
                String key = get_data_between_quotes(kv[0].trim());
                Object value = parseValue(kv[1].trim());
                map.put(key, value);
            }
        }

        return map;
    }

    public static void addKeyValue(Map<String, Object> map, String pair) {
        String[] kv = pair.split(":", 2);
        if (kv.length == 2) {
            String key = get_data_between_quotes(kv[0].trim());
            Object value = parseValue(kv[1].trim());
            map.put(key, value);
        }
    }

    public static List<Map<String, Object>> parseMultipleMaps(String str) {
        List<Map<String, Object>> list = new ArrayList<>();
        int depth = 0;
        int start = -1;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (ch == '}') {
                depth--;
                if (depth == 0 && start != -1) {
                    String block = str.substring(start, i + 1);
                    list.add(parseMap(block));
                    start = -1;
                }
            }
        }
        return list;
    }

    public static List<Object> parseList_(String str) {
        List<Object> list = new ArrayList<>();
        str = get_data_between(str, '[', ']');

        int depth = 0;
        int start = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '[' || ch == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (ch == ']' || ch == '}') {
                depth--;
                if (depth == 0) {
                    String inner = str.substring(start, i + 1).trim();
                    list.add(parseValue(inner));
                    start = i + 1;
                }
            } else if (ch == ',' && depth == 0) {
                String value = str.substring(start, i).trim();
                if (!value.isEmpty()) {
                    list.add(parseValue(value));
                }
                start = i + 1;
            }
        }

        if (start < str.length()) {
            String value = str.substring(start).trim();
            if (!value.isEmpty()) {
                list.add(parseValue(value));
            }
        }

        return list;
    }

    // ------------------------------------------------------------------------
    // 4. ----------------------- Output Printers -----------------------------
    // ------------------------------------------------------------------------

    public static void print(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void print(List<Map<String, Object>> listOfMaps) {
        for (Map<String, Object> map : listOfMaps) {
            print(map);
            System.out.println("-----");
        }
    }
}