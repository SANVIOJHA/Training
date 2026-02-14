package com.lpu.service;


import java.util.List;

public class CalSer {

    // Method to compare two arrays
    public int[] getNumbers() {
        return new int[]{23, 25};
    }

    // Method to compare two integers
    public boolean compareNumbers(int a, int b) {
        return a == b;
    }

    // Method with delay (for timeout testing)
    public void delayedOperation() throws InterruptedException {
        Thread.sleep(1000);  // 1 second delay
        System.out.println("Operation Completed");
    }

    // Method to return sum of list
    public int sumList(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}

