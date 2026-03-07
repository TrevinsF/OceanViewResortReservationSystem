package com.oceanview.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelpServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;

    @InjectMocks private HelpServlet servlet;

    @BeforeEach
    void setup() throws Exception {
        when(request.getRequestDispatcher("help.jsp")).thenReturn(dispatcher);
    }

    @Test
    void shouldForwardToHelpPage() throws Exception {
        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }
}