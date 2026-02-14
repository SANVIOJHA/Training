package com.lpu.service;

import org.junit.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalSerTest {

    static CalSer calSer;

    @BeforeAll
    static void beforeClass() {
        calSer = new CalSer();
        System.out.println("Before All Tests - Setup");
    }

    @AfterAll
    static void afterClass() {
        System.out.println("After All Tests - Cleanup");
    }

    @Test
  public  void testArray() {
        int[] expected = {23, 25};
        int[] actual = calSer.getNumbers();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
   public void testCompareNumbers() {
        Assertions.assertTrue(calSer.compareNumbers(10, 10));
    }

    @Test
    public void testSumList() {
        List<Integer> list = Arrays.asList(10, 20, 30);
        int result = calSer.sumList(list);

        Assertions.assertEquals(60, result);
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
   public  void testDelayedOperation() throws InterruptedException {
        calSer.delayedOperation();
    }
}
