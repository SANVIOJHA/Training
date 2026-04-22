package com.example.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();
    private static final double DELTA = 0.0001;

    @Test
    @DisplayName("Test Addition with positive, negative, decimal and zero values")
    void testAdd() {
        assertAll(
                () -> assertEquals(10, calculator.add(5, 5), DELTA),
                () -> assertEquals(0, calculator.add(-5, 5), DELTA),
                () -> assertEquals(5.5, calculator.add(2.2, 3.3), DELTA),
                () -> assertNotEquals(9, calculator.add(4, 4), DELTA)
        );
    }

    @Test
    @DisplayName("Test Subtraction with edge cases")
    void testSubtract() {
        assertAll(
                () -> assertEquals(0, calculator.subtract(5, 5), DELTA),
                () -> assertEquals(-10, calculator.subtract(-5, 5), DELTA),
                () -> assertEquals(1.1, calculator.subtract(5.5, 4.4), DELTA)
        );
    }

    @Test
    @DisplayName("Test Multiplication")
    void testMultiply() {
        assertAll(
                () -> assertEquals(25, calculator.multiply(5, 5), DELTA),
                () -> assertEquals(-25, calculator.multiply(-5, 5), DELTA),
                () -> assertEquals(0, calculator.multiply(5, 0), DELTA)
        );
    }

    @Test
    @DisplayName("Test Division")
    void testDivide() {
        assertAll(
                () -> assertEquals(2, calculator.divide(10, 5), DELTA),
                () -> assertEquals(-2, calculator.divide(-10, 5), DELTA),
                () -> assertEquals(2.5, calculator.divide(5, 2), DELTA)
        );
    }

    @Test
    @DisplayName("Division by Zero should throw IllegalArgumentException")
    void testDivideByZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(10, 0)
        );
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    @Test
    @DisplayName("Test Modulus")
    void testModulus() {
        assertAll(
                () -> assertEquals(1, calculator.modulus(5, 2), DELTA),
                () -> assertEquals(0, calculator.modulus(4, 2), DELTA)
        );
    }

    @Test
    @DisplayName("Modulus by Zero should throw IllegalArgumentException")
    void testModulusByZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.modulus(10, 0)
        );
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    @Test
    @DisplayName("Test Power Function")
    void testPower() {
        assertAll(
                () -> assertEquals(8, calculator.power(2, 3), DELTA),
                () -> assertEquals(1, calculator.power(5, 0), DELTA),
                () -> assertEquals(0.25, calculator.power(2, -2), DELTA)
        );
    }

    @Test
    @DisplayName("Test Square Function")
    void testSquare() {
        assertEquals(25, calculator.square(5), DELTA);
    }

    @Test
    @DisplayName("Test Positive and Negative Checks")
    void testPositiveNegative() {
        assertAll(
                () -> assertTrue(calculator.isPositive(10)),
                () -> assertFalse(calculator.isPositive(-5)),
                () -> assertTrue(calculator.isNegative(-10)),
                () -> assertFalse(calculator.isNegative(5))
        );
    }
}
