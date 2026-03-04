<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Reservation - Ocean View Resort</title>
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
        <h2>Create New Reservation</h2>
    </div>
    <p class="sub-text">Enter guest and booking details below.</p>

    <c:if test="${not empty requestScope.successMessage}">
        <div class="success-message">
            <i class="fas fa-check-circle text-2xl text-green-400"></i>
            <c:out value="${requestScope.successMessage}"/>
        </div>
    </c:if>

    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="success-message">
        <i class="fas fa-check-circle text-2xl text-green-400"></i>
        <%= request.getAttribute("successMessage") %>
    </div>
    <% } %>

    <c:if test="${not empty requestScope.errorMessage}">
        <div class="error-message">
            <c:out value="${requestScope.errorMessage}"/>
        </div>
    </c:if>

    <form action="addReservation" method="post" class="form-container max-w-3xl" id="reservationForm">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">

            <div class="form-group md:col-span-2">
                <label for="guestName" class="form-label">Guest Name</label>
                <input type="text" id="guestName" name="guestName" class="form-input" required placeholder="Full name">
            </div>

            <div class="form-group">
                <label for="contactNumber" class="form-label">Contact Number</label>
                <input type="tel" id="contactNumber" name="contactNumber" class="form-input" required placeholder="+94xxxxxxxxx">
            </div>

            <div class="form-group">
                <label for="roomType" class="form-label">Room Type</label>
                <select id="roomType" name="roomType" class="form-input" required>
                    <option value="">-- Select Room Type --</option>
                    <option value="Standard">Standard (Rs. 10,000 / night)</option>
                    <option value="Deluxe">Deluxe (Rs. 15,000 / night)</option>
                    <option value="Suite">Suite (Rs. 20,000 / night)</option>
                </select>
            </div>

            <div class="form-group md:col-span-2">
                <label for="address" class="form-label">Address</label>
                <textarea id="address" name="address" rows="3" class="form-input" required maxlength="200" placeholder="Street, City, Country"></textarea>
            </div>

            <div class="form-group">
                <label for="checkInDate" class="form-label">Check-in Date</label>
                <input type="date" id="checkInDate" name="checkInDate" class="form-input" required>
            </div>

            <div class="form-group">
                <label for="checkOutDate" class="form-label">Check-out Date</label>
                <input type="date" id="checkOutDate" name="checkOutDate" class="form-input" required>
            </div>

        </div>

        <button type="submit" class="form-button px-8 py-3">
            <i class="fas fa-save mr-2"></i> Create Reservation
        </button>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const today = new Date().toISOString().split('T')[0];
        const checkIn = document.getElementById('checkInDate');
        const checkOut = document.getElementById('checkOutDate');

        checkIn.min = today;
        checkIn.addEventListener('change', () => {
            checkOut.min = checkIn.value;
        });
    });

    <c:if test="${not empty requestScope.successMessage}">
    document.getElementById('reservationForm').reset();
    </c:if>
</script>
</body>
</html>