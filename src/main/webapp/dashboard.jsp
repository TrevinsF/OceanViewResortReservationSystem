<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Ocean View Resort</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<c:if test="${sessionScope.currentUser == null}">
    <c:redirect url="login.jsp"/>
</c:if>

<%@ include file="header.jsp" %>

<div class="main-content">
    <div class="title-text">
        <h2>Dashboard</h2>
    </div>
    <p class="sub-text">Select an option below to manage room reservations:</p>

    <div class="tiles-grid">
        <a href="addReservation" class="tile-card">
            <i class="fas fa-calendar-plus tile-icon" style="color: #4ade80;"></i>
            <h3 class="tile-title">New Reservation</h3>
            <p class="tile-description">Create a new guest booking</p>
        </a>

        <a href="displayReservation" class="tile-card">
            <i class="fas fa-search tile-icon" style="color: #60a5fa;"></i>
            <h3 class="tile-title">Find Reservation</h3>
            <p class="tile-description">View reservation details</p>
        </a>

        <a href="calculateBill" class="tile-card">
            <i class="fas fa-file-invoice-dollar tile-icon" style="color: #a78bfa;"></i>
            <h3 class="tile-title">Calculate Bill</h3>
            <p class="tile-description">Generate guest bill</p>
        </a>

        <a href="help" class="tile-card">
            <i class="fas fa-question-circle tile-icon" style="color: #fa8b8b;"></i>
            <h3 class="tile-title">Help</h3>
            <p class="tile-description">Usage guidelines</p>
        </a>
    </div>
</div>
</body>
</html>