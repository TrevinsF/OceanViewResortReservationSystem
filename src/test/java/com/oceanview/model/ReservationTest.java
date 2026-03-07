package com.oceanview.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    @DisplayName("Reservation object should correctly store and retrieve values")
    void testReservationGettersAndSetters() {
        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 86400000L * 3); // +3 days

        Reservation res = new Reservation();
        res.setId(5);
        res.setReservationNumber(1001);
        res.setGuestName("John Doe");
        res.setAddress("123 Beach Rd, Galle");
        res.setContactNumber("+94771234567");
        res.setRoomType("Deluxe");
        res.setCheckInDate(checkIn);
        res.setCheckOutDate(checkOut);

        assertEquals(5, res.getId());
        assertEquals(1001, res.getReservationNumber());
        assertEquals("John Doe", res.getGuestName());
        assertEquals("Deluxe", res.getRoomType());
        assertSame(checkIn, res.getCheckInDate());
        assertSame(checkOut, res.getCheckOutDate());
    }

    @Test
    @DisplayName("Default constructor should create empty object")
    void testDefaultConstructor() {
        Reservation res = new Reservation();
        assertNull(res.getGuestName());
        assertEquals(0, res.getReservationNumber());
    }
}