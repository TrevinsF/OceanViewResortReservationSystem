package com.oceanview.servlet;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateBillServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ReservationDAO reservationDAO;

    @InjectMocks
    private CalculateBillServlet servlet;

    @BeforeEach
    void setUp() throws Exception {
        when(request.getRequestDispatcher("/calculateBill.jsp")).thenReturn(dispatcher);
        servlet.init(); // calls reservationDAO = new ReservationDAO()
        // Override DAO with mock
        java.lang.reflect.Field field = CalculateBillServlet.class.getDeclaredField("reservationDAO");
        field.setAccessible(true);
        field.set(servlet, reservationDAO);
    }

    @Test
    void shouldCalculateBillForDeluxeRoom3Nights() throws Exception {
        // Arrange
        Reservation res = new Reservation();
        res.setReservationNumber(1005);
        res.setRoomType("Deluxe");
        res.setCheckInDate(new Date());
        res.setCheckOutDate(new Date(System.currentTimeMillis() + 3L * 86400000));

        when(request.getParameter("reservationNumber")).thenReturn("1005");
        when(reservationDAO.getReservationByNumber(1005)).thenReturn(res);

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("rate", 15000);
        verify(request).setAttribute("nights", 3L);
        verify(request).setAttribute("total", 45000L);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void shouldSetErrorWhenReservationNotFound() throws Exception {
        when(request.getParameter("reservationNumber")).thenReturn("9999");
        when(reservationDAO.getReservationByNumber(9999)).thenReturn(null);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("errorMessage"), contains("No reservation found"));
        verify(dispatcher).forward(request, response);
    }
}