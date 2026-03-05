package com.oceanview.servlet;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/displayReservation")
public class DisplayReservationServlet extends HttpServlet {

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
                    request.setAttribute("singleReservation", reservation);
                } else {
                    request.setAttribute("errorMessage", "No reservation found with number: " + reservationNumber);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid reservation number format.");
            }
        } else {
            List<Reservation> allReservations = reservationDAO.getAllReservations();
            request.setAttribute("allReservations", allReservations);
        }

        request.getRequestDispatcher("/displayReservation.jsp").forward(request, response);
    }
}