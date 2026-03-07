package com.oceanview.dao;

import com.oceanview.model.Reservation;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationDAOTest {
    private static final String H2_URL = "jdbc:h2:mem:oceanview_test;DB_CLOSE_DELAY=-1;MODE=MySQL;USER=root;PASSWORD=1234";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static ReservationDAO dao;

    @BeforeAll
    static void setupDatabase() throws Exception {

        System.setProperty("test.db.url", H2_URL);

        dao = new ReservationDAO();

        try (Connection conn = DriverManager.getConnection(H2_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create the table structure specifically for the H2 test environment
            stmt.execute("CREATE TABLE IF NOT EXISTS reservations (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "reservation_number INT UNIQUE NOT NULL, " +
                    "guest_name VARCHAR(100), " +
                    "address VARCHAR(200), " +
                    "contact_number VARCHAR(20), " +
                    "room_type VARCHAR(50), " +
                    "check_in_date DATE, " +
                    "check_out_date DATE" +
                    ")");
        }
    }

    @BeforeEach
    void clearData() throws SQLException {
        // Truncate table before each test to ensure a clean state
        try (Connection conn = DriverManager.getConnection(H2_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute("TRUNCATE TABLE reservations");
        }
    }

    @Test
    @Order(1)
    @DisplayName("Should successfully add a reservation to H2 and assign a number")
    void testAddReservation() throws Exception {
        Reservation res = createTestReservation("John Doe", "Luxury");

        boolean success = dao.addReservation(res);

        assertTrue(success, "DAO should return true on successful insertion");
        assertTrue(res.getReservationNumber() > 0, "Reservation number should be generated and set in the object");

        // Verification that data is in H2 and not leaked elsewhere
        try (Connection conn = DriverManager.getConnection(H2_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reservations")) {
            rs.next();
            assertEquals(1, rs.getInt(1), "H2 should contain exactly 1 record");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Should retrieve a specific reservation from H2 by its number")
    void testGetReservationByNumber() throws Exception {
        Reservation inserted = createTestReservation("Alice Smith", "Standard");
        dao.addReservation(inserted);

        Reservation found = dao.getReservationByNumber(inserted.getReservationNumber());

        assertNotNull(found, "Reservation should be found in the H2 database");
        assertEquals("Alice Smith", found.getGuestName());
        assertEquals("Standard", found.getRoomType());
    }

    @Test
    @Order(3)
    @DisplayName("Should return null when searching for a non-existent reservation number")
    void testGetNonExistingReservation() {
        Reservation res = dao.getReservationByNumber(999999);
        assertNull(res, "Result should be null for a number that doesn't exist");
    }

    private Reservation createTestReservation(String name, String roomType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date in = sdf.parse("2026-05-20");
            Date out = sdf.parse("2026-05-25");
            return new Reservation(name, "123 Test Lane", "+123456789", roomType, in, out);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create test reservation date", e);
        }
    }

    @AfterAll
    static void tearDown() {
        // Clean up the system property after tests are done
        System.clearProperty("test.db.url");
    }
}