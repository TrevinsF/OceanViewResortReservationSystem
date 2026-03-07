package com.oceanview.dao;

import com.oceanview.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private static UserDAO dao;

    @BeforeAll
    static void init() {
        dao = new UserDAO();
    }

    @Test
    void shouldAuthenticateValidUser() {
        User user = dao.authenticateUser("admin", "123");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void shouldReturnNullForInvalidCredentials() {
        User user = dao.authenticateUser("admin", "wrongpass");
        assertNull(user);
    }
}