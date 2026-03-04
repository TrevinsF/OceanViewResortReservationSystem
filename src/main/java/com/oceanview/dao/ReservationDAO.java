package com.oceanview.dao;

import com.oceanview.model.Reservation;
import com.oceanview.util.DBConnection;

import java.sql.*;

public class ReservationDAO {

    public boolean addReservation(Reservation reservation) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String maxSQL = "SELECT MAX(reservation_number) AS max_num FROM reservations";
            pstmt = conn.prepareStatement(maxSQL);
            rs = pstmt.executeQuery();
            int max = 0;
            if (rs.next()) {
                max = rs.getInt("max_num");
            }
            int resNum = max + 1;
            reservation.setReservationNumber(resNum);

            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();

            String insertSQL = "INSERT INTO reservations (reservation_number, guest_name, address," +
                    " contact_number, room_type, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, resNum);
            pstmt.setString(2, reservation.getGuestName());
            pstmt.setString(3, reservation.getAddress());
            pstmt.setString(4, reservation.getContactNumber());
            pstmt.setString(5, reservation.getRoomType());
            pstmt.setDate(6, new java.sql.Date(reservation.getCheckInDate().getTime()));
            pstmt.setDate(7, new java.sql.Date(reservation.getCheckOutDate().getTime()));

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeResources(conn, pstmt, rs);
        }
    }
}