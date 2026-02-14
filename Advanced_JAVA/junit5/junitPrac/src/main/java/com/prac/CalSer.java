package com.prac;

import java.util.List;

public class CalSer {

    public int[] getNumbers() {
        return new int[]{23, 25};
    }

    public boolean compareNumbers(int a, int b) {
        return a == b;
    }

    public void delayedOperation() throws InterruptedException {
        Thread.sleep(1000);  // 1 second delay
        System.out.println("Operation Completed");
    }

    public int sumList(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    // Method that throws RuntimeException
    public void checkPositive(int number) {
        if (number < 0) {
            throw new RuntimeException("Number must be positive");
        }
    }

    // Method for assertDoesNotThrow
    public void safeMethod() {
        System.out.println("No Exception Here");
    }
}
