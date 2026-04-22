package com.prac.lpu;

import com.prac.CalSer;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

// This annotation allows us to control execution order of test methods
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalSerTest {

    static CalSer calSer;

    // Runs ONLY ONCE before all test methods
    // Must be static in JUnit 5
    @BeforeAll
    static void beforeAll() {
        calSer = new CalSer();  // create object once
        System.out.println("Before All Tests - Setup Once");
    }

    // Runs ONLY ONCE after all test methods
    @AfterAll
    static void afterAll() {
        System.out.println("After All Tests - Cleanup Once");
    }

    // Runs BEFORE every test method
    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each Test");
    }

    // Runs AFTER every test method
    @AfterEach
    void afterEach() {
        System.out.println("After Each Test");
    }

    // @Test marks this as a test method
    @Test
    @Order(1)   // Controls execution order
    void testArray() {

        // Expected result
        int[] expected = {23, 25};

        // Actual result from method
        int[] actual = calSer.getNumbers();

        // Checks if both arrays are equal
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void testCompareNumbers() {

        // assertTrue checks condition is true
        assertTrue(calSer.compareNumbers(10, 10));

        // assertFalse checks condition is false
        assertFalse(calSer.compareNumbers(10, 20));
    }

    @Test
    @Order(3)
    void testSumList() {

        List<Integer> list = Arrays.asList(10, 20, 30);

        int result = calSer.sumList(list);

        // Checks expected value equals actual value
        assertEquals(60, result);
    }

    @Test
    @Order(4)

    // Fails if method execution takes more than 2 seconds
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testDelayedOperation() throws InterruptedException {
        calSer.delayedOperation();
    }

    @Test
    @Order(5)
    void testRuntimeException() {

        // assertThrows verifies that exception is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            calSer.checkPositive(-5);  // this should throw exception
        });

        // Verifies exception message
        assertEquals("Number must be positive", exception.getMessage());
    }

    @Test
    @Order(6)
    void testDoesNotThrow() {

        // Verifies that no exception is thrown
        assertDoesNotThrow(() -> calSer.safeMethod());
    }

    @Test
    @Order(7)
    void testAssertAll() {

        // assertAll runs multiple assertions together
        // Even if one fails, others will execute
        assertAll("Multiple Assertions",
                () -> assertEquals(2, calSer.getNumbers().length),
                () -> assertNotNull(calSer),
                () -> assertTrue(calSer.compareNumbers(5, 5))
        );
    }
}
