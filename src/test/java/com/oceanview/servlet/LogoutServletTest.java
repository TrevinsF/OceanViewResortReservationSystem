package com.oceanview.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogoutServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;

    @InjectMocks private LogoutServlet servlet;

    @BeforeEach
    void setup() {
        when(request.getSession(false)).thenReturn(session);
    }

    @Test
    void logoutShouldInvalidateSessionAndRedirect() throws Exception {
        servlet.doGet(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect(contains("logout=true"));
    }
}