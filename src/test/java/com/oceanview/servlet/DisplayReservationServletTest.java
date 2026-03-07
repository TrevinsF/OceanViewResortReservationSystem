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

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DisplayReservationServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ReservationDAO reservationDAO;

    @InjectMocks private DisplayReservationServlet servlet;

    @BeforeEach
    void setup() throws Exception {
        when(request.getRequestDispatcher("/displayReservation.jsp")).thenReturn(dispatcher);
    }

    @Test
    void shouldReturnSingleReservationWhenNumberProvided() throws Exception {
        Reservation res = new Reservation();
        res.setReservationNumber(1001);
        when(request.getParameter("reservationNumber")).thenReturn("1001");
        when(reservationDAO.getReservationByNumber(1001)).thenReturn(res);

        servlet.doGet(request, response);

        verify(request).setAttribute("singleReservation", res);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void shouldReturnAllReservationsWhenNoNumber() throws Exception {
        when(request.getParameter("reservationNumber")).thenReturn(null);
        when(reservationDAO.getAllReservations()).thenReturn(Arrays.asList(new Reservation()));

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("allReservations"), anyList());
        verify(dispatcher).forward(request, response);
    }
}