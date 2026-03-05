package com.oceanview.dao;

import com.oceanview.model.Reservation;
import com.oceanview.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Reservation getReservationByNumber(int reservationNumber) {
        String SQL = "SELECT * FROM reservations WHERE reservation_number = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Reservation reservation = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, reservationNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setReservationNumber(rs.getInt("reservation_number"));
                reservation.setGuestName(rs.getString("guest_name"));
                reservation.setAddress(rs.getString("address"));
                reservation.setContactNumber(rs.getString("contact_number"));
                reservation.setRoomType(rs.getString("room_type"));
                reservation.setCheckInDate(rs.getDate("check_in_date"));
                reservation.setCheckOutDate(rs.getDate("check_out_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(conn, pstmt, rs);
        }
        return reservation;
    }

    public List<Reservation> getAllReservations() {
        String SQL = "SELECT * FROM reservations";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setReservationNumber(rs.getInt("reservation_number"));
                reservation.setGuestName(rs.getString("guest_name"));
                reservation.setAddress(rs.getString("address"));
                reservation.setContactNumber(rs.getString("contact_number"));
                reservation.setRoomType(rs.getString("room_type"));
                reservation.setCheckInDate(rs.getDate("check_in_date"));
                reservation.setCheckOutDate(rs.getDate("check_out_date"));
                reservations.add(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(conn, stmt, rs);
        }
        return reservations;
    }
}