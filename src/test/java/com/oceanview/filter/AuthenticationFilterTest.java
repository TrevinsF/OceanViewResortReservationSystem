package com.oceanview.filter;

import jakarta.servlet.FilterChain;
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
class AuthenticationFilterTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private FilterChain chain;
    @Mock private HttpSession session;

    @InjectMocks private AuthenticationFilter filter;

    @BeforeEach
    void setup() {
        when(request.getRequestURI()).thenReturn("/calculateBill");
    }

    @Test
    void unauthenticatedUserShouldBeRedirectedToLogin() throws Exception {
        when(request.getSession(false)).thenReturn(null);

        filter.doFilter(request, response, chain);

        verify(response).sendRedirect(contains("/login.jsp"));
        verify(chain, never()).doFilter(any(), any());
    }

    @Test
    void authenticatedUserShouldProceed() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("currentUser")).thenReturn(new Object());
        when(request.getRequestURI()).thenReturn("/dashboard.jsp");

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    void loginPageShouldAlwaysBeAccessible() throws Exception {
        when(request.getRequestURI()).thenReturn("/login.jsp");

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }
}