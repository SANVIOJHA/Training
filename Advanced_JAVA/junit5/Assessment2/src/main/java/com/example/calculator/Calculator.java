package com.example.calculator;

public class Calculator {

    // Stateless → Thread-safe

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public double modulus(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a % b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double square(double number) {
        return number * number;
    }

    public boolean isPositive(double number) {
        return number > 0;
    }

    public boolean isNegative(double number) {
        return number < 0;
    }
}
