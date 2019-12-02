package edu.fractioncalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public final class FractionCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FractionCalculator.class);

    public static String calculateExpression(String expression) {
        expression = infixToPostfix(expression);
        LOGGER.info("=====================================");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            LOGGER.info("> Parse char {} at expression", c);
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                String multipleCharsValue = getMultipleCharsValue(expression.substring(i));
                stack.push(Integer.valueOf(multipleCharsValue));
                i += multipleCharsValue.length() - 1;
                LOGGER.info("> Push to stack: {}", c);
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.empty() ? 0 : stack.pop();
                if (c == '*') {
                    stack.push(operand1 * operand2);
                } else if (c == '/') {
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

        StringBuilder result = new StringBuilder();
        Stack<Character> operationSymbols = new Stack<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            LOGGER.info("> Parse char {} at expression", c);
            if (Character.isLetter(c)) {
                LOGGER.info("Infix expression contains letters: {}", expression);
                return "Wrong input(only digits supported)";
            }
            if (Character.isDigit(c)) {
                String multipleCharsValue = getMultipleCharsValue(expression.substring(i));
                result.append(multipleCharsValue);
                result.append(" ");
                LOGGER.info("Add string '{} ' to result", multipleCharsValue);
                i += multipleCharsValue.length() - 1;
            } else if (c == '(') {
                operationSymbols.push(c);
            }
            else if (c == ')') {
                while (!operationSymbols.isEmpty() && operationSymbols.peek() != '(')
                    result.append(operationSymbols.pop());
                if (!operationSymbols.isEmpty() && operationSymbols.peek() != '(')
                    return "Wrong input";
                else
                    operationSymbols.pop();
            } else {
                while (!operationSymbols.isEmpty() && operationPriority(c) <= operationPriority(operationSymbols.peek())) {
                    if (operationSymbols.peek() == '(')
                        return "Wrong input";
                    result.append(operationSymbols.pop());
                }
                LOGGER.info("Add operation symbol '{}' to result", c);
                operationSymbols.push(c);
            }
        }

        while (!operationSymbols.isEmpty()) {
            if (operationSymbols.peek() == '(')
                return "Wrong input";
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