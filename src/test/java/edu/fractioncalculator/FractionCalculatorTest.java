package edu.fractioncalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionCalculatorTest {
    // Infix to postfix tests
    @Test
    public void infixToPostfixSimpleTest() {
        assertEquals(FractionCalculator.infixToPostfix("5+3"), "5 3 +");
        assertEquals(FractionCalculator.infixToPostfix("53-3"), "53 3 -");
        assertEquals(FractionCalculator.infixToPostfix("3*3"), "3 3 *");
        assertEquals(FractionCalculator.infixToPostfix("3/3"), "3 3 /");
    }

    @Test
    public void infixToPostfixWhiteSpaceTest() {
        assertEquals(FractionCalculator.infixToPostfix("5 +3 "), "5 3 +");
        assertEquals(FractionCalculator.infixToPostfix("        5   +      3 "), "5 3 +");
        assertEquals(FractionCalculator.infixToPostfix("  5 + 3 "), "5 3 +");
    }

    @Test
    public void infixToPostfixSameNumbersTest() {
        assertEquals(FractionCalculator.infixToPostfix("53"), "53 ");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void infixToPostfixEmptyInputTest() {
        assertEquals(FractionCalculator.infixToPostfix(""), "Expression length must be greater than zero)");
    }

    @Test
    public void infixToPostfixMediumExpressionTest() {
        assertEquals(FractionCalculator.infixToPostfix("5-4+6-3*2"), "5 4 -6 +3 2 *-");
        assertEquals(FractionCalculator.infixToPostfix("5 *6 /3 -2"), "5 6 *3 /2 -");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void infixToPostfixWrongCharTest() {
        assertEquals(FractionCalculator.infixToPostfix("5 -c"), "Wrong input(only digits supported)");
        assertEquals(FractionCalculator.infixToPostfix("5 % 2"), "Wrong input(only digits supported)");
    }

    @Test
    public void infixToPostfixTooMuchOperationSymbolsTest() {
        //TODO idk (mb check next symbol != operation.symbol)
//        assertEquals(FractionCalculator.infixToPostfix("5++6"), "Wrong input");
    }

    @Test
    public void infixToPostfixSimpleBracketsTest() {
        assertEquals(FractionCalculator.infixToPostfix("(5)"), "5 ");
        assertEquals(FractionCalculator.infixToPostfix("((5))"), "5 ");
        assertEquals(FractionCalculator.infixToPostfix("(((5)))"), "5 ");
        assertEquals(FractionCalculator.infixToPostfix("(2) + (1)"), "2 1 +");
        assertEquals(FractionCalculator.infixToPostfix("(2+2) + (3+3)"), "2 2 +3 3 ++");
    }

    @Test
    public void infixToPostfixBracketsTest() {
        assertEquals(FractionCalculator.infixToPostfix("(1 + 5)"), "1 5 +");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void infixToPostfixUnbalancedBracketsTest() {
        assertEquals(FractionCalculator.infixToPostfix("(1 + 2))"), "Wrong input");
        assertEquals(FractionCalculator.infixToPostfix("((1 + 2)"), "Wrong input");
        assertEquals(FractionCalculator.infixToPostfix("1 + 2)"), "Wrong input");
        assertEquals(FractionCalculator.infixToPostfix("(1 + 2"), "Wrong input");
        assertEquals(FractionCalculator.infixToPostfix("(1) + (2"), "Wrong input");
        assertEquals(FractionCalculator.infixToPostfix("1) + (2"), "Wrong input");
        assertEquals(FractionCalculator.infixToPostfix("(1 + )2"), "Wrong input");
    }

    // Calculator expression tests
    @Test
    public void calculateExpressionSimpleTest() {
        assertEquals(FractionCalculator.calculateExpression("5+3"), "8");
        assertEquals(FractionCalculator.calculateExpression("53-3"), "50");
        assertEquals(FractionCalculator.calculateExpression("3*3"), "9");
        assertEquals(FractionCalculator.calculateExpression("3/3"), "1");
    }

    @Test
    public void calculateExpressionSameNumbersTest() {
        assertEquals(FractionCalculator.calculateExpression("53"), "53");
    }

    @Test
    public void calculateExpressionExpressionTest() {
        assertEquals(FractionCalculator.calculateExpression("5 -4 +6 -3 -1- 0 +8"), "11");
        assertEquals(FractionCalculator.calculateExpression("5 *6 /3 -2 +1+ 71 +13"), "93");
    }

    @Test(expected = ArithmeticException.class)
    public void calculateExpressionDividedByZeroTest() {
        assertEquals(FractionCalculator.calculateExpression("5/0"), "50/");
    }
}
