package edu.fractioncalculator;

import java.util.Stack;

public final class FractionCalculator {
    public static String calculateExpression(String expression) {
        expression = infixToPostfix(expression);
        System.out.println("Postfix expression: " + expression);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
                System.out.println("...Push to stack: " + Character.getNumericValue(c));
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
                    System.out.println("Push to stack: " + operand1 + " " + operand2 + " " + (operand1 - operand2));
                }
            }
        }
        return stack.pop().toString();
    }

    public static String infixToPostfix(String expression) {
        expression = expression.replaceAll("\\s+", "");
        StringBuilder result = new StringBuilder();
        Stack<Character> operationSymbols = new Stack<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (Character.isLetter(c))
                return "Wrong input(only digits supported)";
            if (Character.isDigit(c))
                result.append(c);
            else if (c == '(')
                operationSymbols.push(c);
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
                operationSymbols.push(c);
            }
        }

        while (!operationSymbols.isEmpty()) {
            if (operationSymbols.peek() == '(')
                return "Wrong input";
            result.append(operationSymbols.pop());
        }

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
}
