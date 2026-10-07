package org.example.module4_assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Evaluates arithmetic expressions made of whole numbers, + - * /, and parentheses.
 * Example: "8/(3-8/3)" evaluates to 24.0
 *
 * The expression is read using these rules, which give * and / priority over + and -,
 * and make anything in parentheses get worked out first:
 *   expression = term   { (+ or -) term }
 *   term       = factor { (* or /) factor }
 *   factor     = number  or  ( expression )
 */
public class ExpressionEvaluator {

    private final String expr;  // the expression with all spaces removed
    private int pos;            // index of the next character to read

    private ExpressionEvaluator(String expression) {
        this.expr = expression.replaceAll("\\s", "");
        this.pos = 0;
    }

    /**
     * Calculates the value of an expression.
     *
     * @param expression the expression typed by the user, e.g. "(3+5)*3"
     * @return the result as a double (so division like 8/3 keeps its decimals)
     * @throws IllegalArgumentException if the expression is not valid
     */
    public static double evaluate(String expression) {
        ExpressionEvaluator evaluator = new ExpressionEvaluator(expression);
        double result = evaluator.parseExpression();

        // Anything left over means the expression was not fully valid, e.g. "3+5)"
        if (evaluator.pos < evaluator.expr.length()) {
            throw new IllegalArgumentException("Unexpected character: " + evaluator.expr.charAt(evaluator.pos));
        }
        return result;
    }

    /**
     * Finds every whole number written in the expression, in order.
     * Example: "8/(3-8/3)" gives [8, 3, 8, 3]
     *
     * @param expression the expression typed by the user
     * @return a list of the numbers found
     */
    public static List<Integer> extractNumbers(String expression) {
        List<Integer> numbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(expression);
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        return numbers;
    }

    /**
     * expression = term { (+ or -) term }
     * Handles addition and subtraction (lowest priority).
     */
    private double parseExpression() {
        double value = parseTerm();
        while (pos < expr.length()) {
            char op = expr.charAt(pos);
            if (op == '+') {
                pos++;
                value += parseTerm();
            } else if (op == '-') {
                pos++;
                value -= parseTerm();
            } else {
                break;
            }
        }
        return value;
    }

    /**
     * term = factor { (* or /) factor }
     * Handles multiplication and division (higher priority than + and -).
     */
    private double parseTerm() {
        double value = parseFactor();
        while (pos < expr.length()) {
            char op = expr.charAt(pos);
            if (op == '*') {
                pos++;
                value *= parseFactor();
            } else if (op == '/') {
                pos++;
                value /= parseFactor();
            } else {
                break;
            }
        }
        return value;
    }

    /**
     * factor = number or ( expression )
     * Handles a single number, or a whole expression inside parentheses.
     */
    private double parseFactor() {
        if (pos >= expr.length()) {
            throw new IllegalArgumentException("The expression is incomplete");
        }

        // Parentheses: work out the expression inside them first
        if (expr.charAt(pos) == '(') {
            pos++;
            double value = parseExpression();
            if (pos >= expr.length() || expr.charAt(pos) != ')') {
                throw new IllegalArgumentException("Missing closing parenthesis");
            }
            pos++;
            return value;
        }

        // Number: read all the digits in a row
        int start = pos;
        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }
        if (start == pos) {
            throw new IllegalArgumentException("Unexpected character: " + expr.charAt(pos));
        }
        return Integer.parseInt(expr.substring(start, pos));
    }
}
