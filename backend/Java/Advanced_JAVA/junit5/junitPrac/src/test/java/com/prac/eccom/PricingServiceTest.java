package com.prac.eccom;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {

    PricingService pricingService;

    @BeforeEach
    void setup() {
        pricingService = new PricingService();
    }

    //  Valid order with electronics + books
    @Test
    void testValidOrder() {

        List<OrderItem> items = Arrays.asList(
                new OrderItem(1000, 1, "ELECTRONICS"), // 1000 + 18% = 1180
                new OrderItem(500, 1, "BOOKS")         // 500 (no tax)
        );

        double total = pricingService.calculateTotal(items, false, false, null);

        assertEquals(1680, total);
    }

    //  Premium + coupon combined
    @Test
    void testPremiumWithCoupon() {

        List<OrderItem> items = Arrays.asList(
                new OrderItem(2000, 1, "ELECTRONICS") // 2000 + 18% = 2360
        );

        double total = pricingService.calculateTotal(items, true, false, "FLAT200");

        // 2360 - 10% = 2124

        assertEquals(1924, total);
    }

    //  Coupon invalid because total < 1000
    @Test
    void testCouponInvalidBelow1000() {

        List<OrderItem> items = Arrays.asList(
                new OrderItem(500, 1, "BOOKS")
        );

        double total = pricingService.calculateTotal(items, false, false, "FLAT200");

        assertEquals(500, total); // coupon should NOT apply
    }

    //  Edge case: discount makes total negative
    @Test
    void testNegativeTotalEdgeCase() {

        List<OrderItem> items = Arrays.asList(
                new OrderItem(100, 1, "BOOKS")
        );

        double total = pricingService.calculateTotal(items, true, false, "FLAT200");

        // Should never be negative
        assertTrue(total >= 0);
    }

    //  Zero items → exception
    @Test
    void testEmptyOrderException() {

        assertThrows(EmptyOrderException.class, () ->
                pricingService.calculateTotal(Collections.emptyList(), false, false, null)
        );
    }

    //  Parameterized test for tax categories
    @ParameterizedTest
    @CsvSource({
            "ELECTRONICS, 1180",
            "BOOKS, 1000"
    })
    void testTaxByCategory(String category, double expectedTotal) {

        List<OrderItem> items = Arrays.asList(
                new OrderItem(1000, 1, category)
        );

        double total = pricingService.calculateTotal(items, false, false, null);

        assertEquals(expectedTotal, total);
    }
}

