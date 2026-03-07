package com.oceanview.servlet;

import com.oceanview.dao.ReservationDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddReservationServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ReservationDAO reservationDAO;

    @InjectMocks private AddReservationServlet servlet;

    @BeforeEach
    void setup() throws Exception {
        when(request.getRequestDispatcher("addReservation.jsp")).thenReturn(dispatcher);
    }

    @Test
    @DisplayName("Should create reservation with valid data")
    void testValidReservationCreation() throws Exception {
        when(request.getParameter("guestName")).thenReturn("John Doe");
        when(request.getParameter("address")).thenReturn("Galle");
        when(request.getParameter("contactNumber")).thenReturn("+94771234567");
        when(request.getParameter("roomType")).thenReturn("Deluxe");
        when(request.getParameter("checkInDate")).thenReturn("2026-04-10");
        when(request.getParameter("checkOutDate")).thenReturn("2026-04-15");

        when(reservationDAO.addReservation(any())).thenReturn(true);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("successMessage"), contains("Reservation created successfully"));
        verify(dispatcher).forward(request, response);
    }

    @Test
    @DisplayName("Should reject when check-out before check-in")
    void testInvalidDateOrder() throws Exception {
        when(request.getParameter("guestName")).thenReturn("John");
        when(request.getParameter("address")).thenReturn("Galle");
        when(request.getParameter("contactNumber")).thenReturn("123");
        when(request.getParameter("roomType")).thenReturn("Deluxe");
        when(request.getParameter("checkInDate")).thenReturn("2026-04-15");
        when(request.getParameter("checkOutDate")).thenReturn("2026-04-10");

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("errorMessage"), contains("Check-out date must be after"));
        verify(dispatcher).forward(request, response);
    }
}