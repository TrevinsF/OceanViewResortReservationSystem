package com.oceanview.servlet;

import com.oceanview.dao.UserDAO;
import com.oceanview.model.User;
import jakarta.servlet.RequestDispatcher;
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
class LoginServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private HttpSession session;
    @Mock private UserDAO userDAO;

    @InjectMocks private LoginServlet servlet;

    @BeforeEach
    void setup() throws Exception {
        lenient().when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);
    }

    @Test
    void successfulLoginShouldCreateSession() throws Exception {
        User user = new User(1, "admin", "123");
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("123");
        when(userDAO.authenticateUser("admin", "123")).thenReturn(user);
        when(request.getSession()).thenReturn(session);

        servlet.doPost(request, response);

        verify(session).setAttribute("currentUser", user);
        verify(response).sendRedirect(contains("loginSuccess=true"));
    }

    @Test
    void invalidLoginShouldShowError() throws Exception {
        when(request.getParameter("username")).thenReturn("wrong");
        when(request.getParameter("password")).thenReturn("pass");
        when(userDAO.authenticateUser(anyString(), anyString())).thenReturn(null);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("errorMessage"), contains("Invalid username"));
        verify(dispatcher).forward(request, response);
    }
}