<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Help - Ocean View Resort</title>
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
    <h2>Help & Usage Guidelines</h2>
  </div>
  <p class="sub-text">Welcome to the Ocean View Resort Reservation System. This guide explains the main features.</p>

  <div class="help-container">
    <div class="help-card">
      <h3 class="help-title">
        <i class="fas fa-home mr-3"></i> Dashboard
      </h3>
      <p class="help-description">
        Your main control panel. Use the tiles or top navigation to access all reservation features quickly.
      </p>
    </div>

    <div class="help-card">
      <h3 class="help-title">
        <i class="fas fa-calendar-plus mr-3"></i> New Reservation
      </h3>
      <p class="help-description">
        Register new guest bookings with name, contact, room type, and stay dates.
      </p>
      <ul class="help-list">
        <li>Fill in guest details and dates</li>
        <li>System auto-assigns unique reservation number</li>
        <li>Validation prevents invalid check-out dates</li>
      </ul>
    </div>

    <div class="help-card">
      <h3 class="help-title">
        <i class="fas fa-search mr-3"></i> Find Reservation
      </h3>
      <p class="help-description">
        Search and View all details of any reservation.
      </p>
      <ul class="help-list">
        <li>Retrieve full details of any booking using the reservation number</li>
        <li>Overview of all active and past reservations</li>
      </ul>
    </div>

    <div class="help-card">
      <h3 class="help-title">
        <i class="fas fa-file-invoice-dollar mr-3"></i> Calculate Bill
      </h3>
      <p class="help-description">
        Generate bill based on number of nights and room type rate.
      </p>
      <ul class="help-list">
        <li>Enter reservation number</li>
        <li>View stay duration and total amount</li>
        <li>Use Print button for hard copy</li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
