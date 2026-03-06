package com.oceanview.servlet;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet("/calculateBill")
public class CalculateBillServlet extends HttpServlet {

    private ReservationDAO reservationDAO;

    @Override
    public void init() {
        reservationDAO = new ReservationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String resNumberStr = request.getParameter("reservationNumber");

        if (resNumberStr != null && !resNumberStr.trim().isEmpty()) {
            try {
                int reservationNumber = Integer.parseInt(resNumberStr.trim());
                Reservation reservation = reservationDAO.getReservationByNumber(reservationNumber);

                if (reservation != null) {
                    // Calculate nights
                    long diffInMillies = Math.abs(reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime());
                    long nights = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    // Get rate
                    int rate = 0;
                    String roomType = reservation.getRoomType();
                    if ("Standard".equals(roomType)) {
                        rate = 10000;
                    } else if ("Deluxe".equals(roomType)) {
                        rate = 15000;
                    } else if ("Suite".equals(roomType)) {
                        rate = 20000;
                    }

                    long total = nights * rate;

                    request.setAttribute("reservation", reservation);
                    request.setAttribute("nights", nights);
                    request.setAttribute("rate", rate);
                    request.setAttribute("total", total);
                } else {
                    request.setAttribute("errorMessage", "No reservation found with number: " + reservationNumber);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid reservation number format.");
            }
        }

        request.getRequestDispatcher("/calculateBill.jsp").forward(request, response);
    }
}