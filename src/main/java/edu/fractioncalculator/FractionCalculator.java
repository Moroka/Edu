package edu.fractioncalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public final class FractionCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FractionCalculator.class);

    public static String calculateExpression(String expression) {
        expression = infixToPostfix(expression);
        LOGGER.info("=====================================");
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            LOGGER.info("> Parse char {} at expression", c);
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                final String multipleCharsValue = getMultipleCharsValue(expression.substring(i));
                stack.push(Integer.valueOf(multipleCharsValue));
                i += multipleCharsValue.length() - 1;
                LOGGER.info("> Push to stack: {}", c);
            } else {
                final int operand2 = stack.pop();
                final int operand1 = stack.empty() ? 0 : stack.pop();
                if (c == '*') {
                    stack.push(operand1 * operand2);
                } else if (c == '/') {
                    if (operand2 == 0)
                        throw new ArithmeticException("Division by zero");
                    stack.push(operand1 / operand2);
                } else if (c == '+') {
                    stack.push(operand1 + operand2);
                } else if (c == '-') {
                    stack.push(operand1 - operand2);
                }
            }
        }
        return stack.pop().toString();
    }

    public static String infixToPostfix(String expression) {
        expression = expression.replaceAll("\\s+", "");
        LOGGER.info("Infix expression: {}", expression);

        final int expressionLength = expression.length();
        if (expressionLength == 0)
            throw new UnsupportedOperationException("Expression length must be greater than zero");

        final StringBuilder result = new StringBuilder();
        final Stack<Character> operationSymbols = new Stack<>();

        for (int i = 0; i < expressionLength; ++i) {
            final char c = expression.charAt(i);
            LOGGER.info("> Parse char {} at expression", c);
            if (Character.isLetter(c)) {
                throw new UnsupportedOperationException("Expression contains letters");
            }
            if (Character.isDigit(c)) {
                final String multipleCharsValue = getMultipleCharsValue(expression.substring(i));
                result.append(multipleCharsValue);
                result.append(" ");
                LOGGER.info("Add string '{} ' to result", multipleCharsValue);
                i += multipleCharsValue.length() - 1;
            } else if (c == '(') {
                operationSymbols.push(c);
            } else if (c == ')') {
                while (!operationSymbols.isEmpty() && operationSymbols.peek() != '(')
                    result.append(operationSymbols.pop());
                    if (!operationSymbols.isEmpty())
                        operationSymbols.pop();
                    else
                        throw new UnsupportedOperationException("Unbalanced brackets");

            } else {
                if (i != expressionLength -1 && (operationPriority(expression.charAt(i + 1)) > -1))
                    throw new UnsupportedOperationException("Repetitive operation symbols");
                if (operationPriority(c) == -1) {
                    throw new UnsupportedOperationException("Expression contains unsupported symbols");
                }
                while (!operationSymbols.isEmpty() && operationPriority(c) <= operationPriority(operationSymbols.peek())) {
                    if (operationSymbols.peek() == '(')
                        throw new UnsupportedOperationException("Unbalanced brackets");
                    result.append(operationSymbols.pop());
                }
                LOGGER.info("Add operation symbol '{}' to result", c);
                operationSymbols.push(c);
            }
        }

        while (!operationSymbols.isEmpty()) {
            if (operationSymbols.peek() == '(')
                throw new UnsupportedOperationException("Unbalanced brackets");
            result.append(operationSymbols.pop());
        }

        LOGGER.info("========== Postfix result '{}'", result.toString());
        return result.toString();
    }

    private static int operationPriority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
        }
        return -1;
    }

    private static String getMultipleCharsValue(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char c;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            c = s.charAt(i);
            sb.append(c);

            LOGGER.info("Add char '{}'", c);
            i++;
        }
        return sb.toString();
    }
}
