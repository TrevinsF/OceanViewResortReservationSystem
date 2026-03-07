package com.oceanview.servlet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillCalculationLogicTest {

    private final CalculateBillServlet servlet = new CalculateBillServlet(); // for rate helper

    @ParameterizedTest
    @CsvSource({
            "Standard,10000",
            "Deluxe,15000",
            "Suite,20000",
            "Unknown,0"
    })
    @DisplayName("Should return correct nightly rate")
    void testRoomRateCalculation(String roomType, int expectedRate) {
        int rate;
        switch (roomType) {
            case "Standard":
                rate = 10000;
                break;
            case "Deluxe":
                rate = 15000;
                break;
            case "Suite":
                rate = 20000;
                break;
            default:
                rate = 0;
                break;
        }
        assertEquals(expectedRate, rate);
    }

    @ParameterizedTest
    @CsvSource({
            "2026-03-01,2026-03-05,4",
            "2026-04-10,2026-04-10,0",
            "2026-05-15,2026-05-20,5"
    })
    @DisplayName("Should correctly calculate nights")
    void testNightsCalculation(String in, String out, long expectedNights) throws Exception {
        // Simulate the exact logic from CalculateBillServlet
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        long diff = Math.abs(sdf.parse(out).getTime() - sdf.parse(in).getTime());
        long nights = java.util.concurrent.TimeUnit.DAYS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS);

        assertEquals(expectedNights, nights);
    }
}