<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div class="top-bar">
    <div class="header">
        <a href="dashboard.jsp">
            <h1>Ocean View Resort</h1>
        </a>
        <form action="logout" method="get">
            <button type="submit" class="logout-button">Logout</button>
        </form>
    </div>
    <nav class="main-nav">
        <a href="dashboard.jsp" class="nav-link">
            <i class="fas fa-home"></i> Dashboard
        </a>
        <a href="addReservation" class="nav-link">
            <i class="fas fa-calendar-plus"></i> New Reservation
        </a>
        <a href="displayReservation" class="nav-link">
            <i class="fas fa-search"></i> Find Reservation
        </a>
        <a href="calculateBill" class="nav-link">
            <i class="fas fa-file-invoice-dollar"></i> Calculate Bill
        </a>
        <a href="help" class="nav-link">
            <i class="fas fa-question-circle"></i> Help
        </a>
    </nav>
</div>