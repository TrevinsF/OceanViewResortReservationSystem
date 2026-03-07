package com.oceanview.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void shouldReturnValidConnection() throws Exception {
        Connection conn = DBConnection.getConnection();
        assertNotNull(conn);
        assertFalse(conn.isClosed());
        conn.close();
    }
}