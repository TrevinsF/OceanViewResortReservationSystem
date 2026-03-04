package com.oceanview.servlet;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addReservation")
public class AddReservationServlet extends HttpServlet {
    private ReservationDAO reservationDAO;

    public void init() {
        reservationDAO = new ReservationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addReservation.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String guestName = request.getParameter("guestName");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String roomType = request.getParameter("roomType");
        String checkInStr = request.getParameter("checkInDate");
        String checkOutStr = request.getParameter("checkOutDate");

        // Basic validation
        if (guestName == null || guestName.trim().isEmpty() ||
                address == null || address.trim().isEmpty() ||
                contactNumber == null || contactNumber.trim().isEmpty() ||
                roomType == null || roomType.trim().isEmpty() ||
                checkInStr == null || checkOutStr == null) {

            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("addReservation.jsp").forward(request, response);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date checkIn = sdf.parse(checkInStr);
            Date checkOut = sdf.parse(checkOutStr);

            if (checkOut.before(checkIn)) {
                request.setAttribute("errorMessage", "Check-out date must be after check-in date.");
                request.getRequestDispatcher("addReservation.jsp").forward(request, response);
                return;
            }

            Reservation reservation = new Reservation(guestName.trim(), address.trim(),
                    contactNumber.trim(), roomType, checkIn, checkOut);

            boolean success = reservationDAO.addReservation(reservation);

            if (success) {
                request.setAttribute("successMessage",
                        "Reservation created successfully! Reservation Number: " + reservation.getReservationNumber());
                request.getRequestDispatcher("addReservation.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to create reservation. Please try again.");
                request.getRequestDispatcher("addReservation.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid date format or unexpected error.");
            request.getRequestDispatcher("addReservation.jsp").forward(request, response);
        }
    }
}