package com.oceanview.servlet;

import com.oceanview.dao.ReservationDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationEdgeCasesTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ReservationDAO reservationDAO;

    @InjectMocks private AddReservationServlet servlet;

    @BeforeEach
    void setup() throws Exception {
        when(request.getRequestDispatcher("addReservation.jsp")).thenReturn(dispatcher);
    }

    @ParameterizedTest
    @CsvSource({
            " ,Galle,+9477,Deluxe,2026-04-10,2026-04-15",     // empty name
            "John,,+9477,Deluxe,2026-04-10,2026-04-15",      // empty address
            "John,Galle,,Deluxe,2026-04-10,2026-04-15"       // empty contact
    })
    void shouldRejectMissingRequiredFields(String name, String address, String contact,
                                           String roomType, String in, String out) throws Exception {
        when(request.getParameter("guestName")).thenReturn(name);
        when(request.getParameter("address")).thenReturn(address);
        when(request.getParameter("contactNumber")).thenReturn(contact);
        when(request.getParameter("roomType")).thenReturn(roomType);
        when(request.getParameter("checkInDate")).thenReturn(in);
        when(request.getParameter("checkOutDate")).thenReturn(out);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("errorMessage"), contains("All fields are required"));
        verify(dispatcher).forward(request, response);
    }
}