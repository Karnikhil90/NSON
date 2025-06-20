package src.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * =============================================================================
 * SymbolBalancer.java
 * =============================================================================
 *
 * A utility class to analyze and validate balanced symbols (brackets and quotes)
 * within a given string. Especially useful for parsing structured data formats
 * like JSON, where balanced symbols are critical for correctness.
 * @author Nikhil Karmakar
 * @version v1.0
 *
 * PURPOSE:
 * --------
 * ✅ Validate balanced parentheses (), square brackets [], and curly braces {}  
 * ✅ Check for correctly paired single and double quotes  
 * ✅ Retrieve the index positions of matching pairs for detailed analysis  
 *
 * USAGE SCENARIOS:
 * ----------------
 * - Before parsing or tokenizing JSON strings
 * - Detecting malformed nested structures
 * - Providing detailed diagnostics or IDE-like suggestions
 *
 * =============================================================================
 */
public class SymbolBalancer {

    /**
     * Checks if all parentheses in the string are balanced.
     */
    public static boolean checkBrackets(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') stack.push(ch);
            else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Checks if all square brackets in the string are balanced.
     */
    public static boolean checkSquareBrackets(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '[') stack.push(ch);
            else if (ch == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Checks if all curly braces in the string are balanced.
     */
    public static boolean checkCurlyBraces(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '{') stack.push(ch);
            else if (ch == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Checks if all single quotes are balanced (not counting escaped ones).
     */
    public static boolean checkSingleQuotes(String str) {
        boolean inSingleQuote = false;
        for (char ch : str.toCharArray()) {
            if (ch == '\'' && !inSingleQuote) inSingleQuote = true;
            else if (ch == '\'' && inSingleQuote) inSingleQuote = false;
        }
        return !inSingleQuote;
    }

    /**
     * Checks if all double quotes are balanced (not counting escaped ones).
     */
    public static boolean checkDoubleQuotes(String str) {
        boolean inDoubleQuote = false;
        for (char ch : str.toCharArray()) {
            if (ch == '"' && !inDoubleQuote) inDoubleQuote = true;
            else if (ch == '"' && inDoubleQuote) inDoubleQuote = false;
        }
        return !inDoubleQuote;
    }

    /**
     * Checks if all types of brackets and quotes in the string are fully balanced.
     */
    public static boolean isFullyBalanced(String str) {
        return checkBrackets(str)
            && checkSquareBrackets(str)
            && checkCurlyBraces(str)
            && checkSingleQuotes(str)
            && checkDoubleQuotes(str);
    }

    /**
     * Returns the index positions of matching parentheses ( ) in the string.
     */
    public static List<int[]> getBracketPairs(String str) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    pairs.add(new int[]{stack.pop(), i});
                }
            }
        }
        return pairs;
    }

    /**
     * Returns the index positions of matching square brackets [ ] in the string.
     */
    public static List<int[]> getSquareBracketPairs(String str) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '[') {
                stack.push(i);
            } else if (ch == ']') {
                if (!stack.isEmpty()) {
                    pairs.add(new int[]{stack.pop(), i});
                }
            }
        }
        return pairs;
    }

    /**
     * Returns the index positions of matching curly braces { } in the string.
     */
    public static List<int[]> getCurlyBracePairs(String str) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '{') {
                stack.push(i);
            } else if (ch == '}') {
                if (!stack.isEmpty()) {
                    pairs.add(new int[]{stack.pop(), i});
                }
            }
        }
        return pairs;
    }

    /**
     * Returns the index positions of matching single quote (') pairs.
     */
    public static List<int[]> getSingleQuotePairs(String str) {
        List<int[]> pairs = new ArrayList<>();
        int lastIndex = -1;
        boolean inQuote = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\'' && (i == 0 || str.charAt(i - 1) != '\\')) {
                if (!inQuote) {
                    lastIndex = i;
                    inQuote = true;
                } else {
                    pairs.add(new int[]{lastIndex, i});
                    inQuote = false;
                }
            }
        }
        return pairs;
    }

    /**
     * Returns the index positions of matching double quote (") pairs.
     */
    public static List<int[]> getDoubleQuotePairs(String str) {
        List<int[]> pairs = new ArrayList<>();
        int lastIndex = -1;
        boolean inQuote = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"' && (i == 0 || str.charAt(i - 1) != '\\')) {
                if (!inQuote) {
                    lastIndex = i;
                    inQuote = true;
                } else {
                    pairs.add(new int[]{lastIndex, i});
                    inQuote = false;
                }
            }
        }
        return pairs;
    }
}
