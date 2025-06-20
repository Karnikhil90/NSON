package src.utility;
// package utility;

import java.util.ArrayList;
import java.util.List;

public class NSONUtils {
    
    // ------------------------------------------------------------------------
    // 1. --------------------- Basic Utility Methods ------------------------
    // ------------------------------------------------------------------------

    public static String get_data_between(String str, char open, char close) {
        int first = str.indexOf(open);
        int last = str.lastIndexOf(close);
        if (first != -1 && last != -1 && last > first) {
            return str.substring(first + 1, last);
        }
        return str.trim();
    }

    public static String get_data_between_quotes(String str) {
        str = str.trim();
        if (str.length() < 2) return str;

        char openQuote = str.charAt(0);
        char closeQuote = str.charAt(str.length() - 1);

        // If both opening and closing quotes match (either ' or ")
        if ((openQuote == '\'' || openQuote == '"') && openQuote == closeQuote) {
            return str.substring(1, str.length() - 1);
        }

        // If quote is mismatched, attempt to find closing quote of the same type
        if (openQuote == '\'' || openQuote == '"') {
            int end = str.indexOf(openQuote, 1);
            if (end != -1) {
                return str.substring(1, end);
            }
        }

        return str;
    }


    public static String get_data_between_curly_bracket(String data) {
        return get_data_between(data, '{', '}');
    }

    public static List<String> splitTopLevel(String str, char delimiter) {
        List<String> result = new ArrayList<>();
        int depth = 0;
        boolean inQuotes = false;
        char quoteChar = 0;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if ((ch == '\'' || ch == '"')) {
                if (!inQuotes) {
                    inQuotes = true;
                    quoteChar = ch;
                } else if (quoteChar == ch) {
                    inQuotes = false;
                }
            } else if (!inQuotes) {
                if (ch == '{' || ch == '[') {
                    depth++;
                } else if (ch == '}' || ch == ']') {
                    depth--;
                } else if (ch == delimiter && depth == 0) {
                    result.add(current.toString().trim());
                    current.setLength(0);
                    continue;
                }
            }

            current.append(ch);
        }

        if (current.length() > 0) {
            result.add(current.toString().trim());
        }

        return result;
    }

}
