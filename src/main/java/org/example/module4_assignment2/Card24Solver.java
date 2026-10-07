package org.example.module4_assignment2;

/**
 * Finds an expression that makes 24 from four card values, using + - * / and parentheses.
 *
 * It tries every possibility:
 *   - every order of the four numbers (24 orders)
 *   - every choice of operator in the three gaps between them (4 x 4 x 4 = 64 choices)
 *   - every way to group them with parentheses (5 groupings)
 * Each expression is built as text and calculated with ExpressionEvaluator,
 * the same code the Verify button uses.
 */
public class Card24Solver {

    private static final char[] OPERATORS = {'+', '-', '*', '/'};

    // The 5 ways to group four numbers with parentheses.
    // The %s spots are filled in this order: number, operator, number, operator, number, operator, number
    private static final String[] GROUPINGS = {
            "((%s%s%s)%s%s)%s%s",   // ((a?b)?c)?d
            "(%s%s(%s%s%s))%s%s",   // (a?(b?c))?d
            "(%s%s%s)%s(%s%s%s)",   // (a?b)?(c?d)
            "%s%s((%s%s%s)%s%s)",   // a?((b?c)?d)
            "%s%s(%s%s(%s%s%s))"    // a?(b?(c?d))
    };

    /**
     * Checks whether a result equals 24. A small tolerance is allowed because division
     * like 8/3 can't be stored exactly (e.g. 8/(3-8/3) gives 23.99999999999999).
     *
     * @param result the calculated value of an expression
     * @return true if the result is 24
     */
    public static boolean isTwentyFour(double result) {
        return Math.abs(result - 24) < 0.0001;
    }

    /**
     * Searches for an expression that uses each of the four card values once and equals 24.
     *
     * @param cards the four card values
     * @return a solution such as "8/(3-(8/3))", or null if no solution exists
     */
    public static String findSolution(int[] cards) {
        // Pick the four card positions in every possible order (i, j, k, l must all be different)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (i == j || i == k || i == l || j == k || j == l || k == l) {
                            continue;
                        }

                        // Try every operator in each of the three gaps
                        for (char op1 : OPERATORS) {
                            for (char op2 : OPERATORS) {
                                for (char op3 : OPERATORS) {

                                    // Try every way of placing the parentheses
                                    for (String grouping : GROUPINGS) {
                                        String expression = String.format(grouping,
                                                cards[i], op1, cards[j], op2, cards[k], op3, cards[l]);

                                        if (isTwentyFour(ExpressionEvaluator.evaluate(expression))) {
                                            return expression;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
