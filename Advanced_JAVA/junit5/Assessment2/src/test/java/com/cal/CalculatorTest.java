package com.cal;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test Addition with Positive, Negative and Decimal Numbers")
    void testAdd() {
        assertAll(
                () -> assertEquals(10, calculator.add(5, 5)),
                () -> assertEquals(0, calculator.add(-5, 5)),
                () -> assertEquals(5.5, calculator.add(2.2, 3.3), 0.0001)
        );
    }

    @Test
    @DisplayName("Test Subtraction")
    void testSubtract() {
        assertAll(
                () -> assertEquals(5, calculator.subtract(10, 5)),
                () -> assertEquals(-10, calculator.subtract(-5, 5)),
                () -> assertNotEquals(0, calculator.subtract(5, 3))
        );
    }

    @Test
    @DisplayName("Test Multiplication")
    void testMultiply() {
        assertAll(
                () -> assertEquals(25, calculator.multiply(5, 5)),
                () -> assertEquals(-25, calculator.multiply(-5, 5)),
                () -> assertEquals(0, calculator.multiply(5, 0))
        );
    }

    @Test
    @DisplayName("Test Division")
    void testDivide() {
        assertAll(
                () -> assertEquals(2, calculator.divide(10, 5)),
                () -> assertEquals(-2, calculator.divide(-10, 5)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> calculator.divide(10, 0))
        );
    }

    @Test
    @DisplayName("Test Modulus")
    void testModulus() {
        assertAll(
                () -> assertEquals(1, calculator.modulus(10, 3)),
                () -> assertEquals(0, calculator.modulus(10, 5)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> calculator.modulus(10, 0))
        );
    }

    @Test
    @DisplayName("Test Power Function")
    void testPower() {
        assertAll(
                () -> assertEquals(8, calculator.power(2, 3)),
                () -> assertEquals(1, calculator.power(5, 0)),
                () -> assertEquals(0.25, calculator.power(2, -2), 0.0001)
        );
    }

    @Test
    @DisplayName("Test Square Function")
    void testSquare() {
        assertAll(
                () -> assertEquals(25, calculator.square(5)),
                () -> assertEquals(25, calculator.square(-5)),
                () -> assertEquals(0, calculator.square(0))
        );
    }

    @Test
    @DisplayName("Test isPositive Method")
    void testIsPositive() {
        assertAll(
                () -> assertTrue(calculator.isPositive(10)),
                () -> assertFalse(calculator.isPositive(-5)),
                () -> assertFalse(calculator.isPositive(0))
        );
    }

    @Test
    @DisplayName("Test isNegative Method")
    void testIsNegative() {
        assertAll(
                () -> assertTrue(calculator.isNegative(-10)),
                () -> assertFalse(calculator.isNegative(5)),
                () -> assertFalse(calculator.isNegative(0))
        );
    }
}
