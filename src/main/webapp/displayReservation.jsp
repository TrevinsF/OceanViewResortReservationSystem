<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Find Reservation - Ocean View Resort</title>
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
        <h2>Find Reservation</h2>
    </div>
    <p class="sub-text">Search by reservation number or view all current bookings.</p>

    <c:if test="${not empty requestScope.errorMessage}">
        <div class="error-message">
            <c:out value="${requestScope.errorMessage}"/>
        </div>
    </c:if>

    <div class="form-container">
        <form action="displayReservation" method="get" class="flex flex-col sm:flex-row gap-4 items-end">
            <div class="flex-grow">
                <label for="reservationNumber" class="form-label block mb-2">Reservation Number</label>
                <input
                        type="number"
                        id="reservationNumber"
                        name="reservationNumber"
                        class="form-input w-full h-12"
                        required
                        placeholder="Enter reservation number"
                        value="${param.reservationNumber}"
                        min="1">
            </div>

            <div class="flex gap-3">
                <button type="submit"
                        class="form-button px-6 py-3 w-48 h-12 flex items-center justify-center gap-2">
                    <i class="fas fa-search"></i> Search
                </button>

                <a href="displayReservation"
                   class="form-button bg-gray-600 hover:bg-gray-700 px-6 py-3 w-48 h-12 flex items-center justify-center gap-2">
                    <i class="fas fa-sync-alt"></i> Show All
                </a>
            </div>
        </form>
    </div>

    <div class="table-container">
        <c:choose>
            <c:when test="${not empty requestScope.singleReservation}">
                <h3 class="text-xl font-semibold mb-4 text-[#feb47b] flex items-center gap-2">
                    <i class="fas fa-info-circle"></i>
                    Reservation #${requestScope.singleReservation.reservationNumber}
                </h3>
                <table class="data-table w-full">
                    <thead>
                    <tr>
                        <th>Reservation #</th>
                        <th>Guest Name</th>
                        <th>Contact</th>
                        <th>Address</th>
                        <th>Room Type</th>
                        <th>Check-in</th>
                        <th>Check-out</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${requestScope.singleReservation.reservationNumber}</td>
                        <td>${requestScope.singleReservation.guestName}</td>
                        <td>${requestScope.singleReservation.contactNumber}</td>
                        <td>${requestScope.singleReservation.address}</td>
                        <td>${requestScope.singleReservation.roomType}</td>
                        <td>${requestScope.singleReservation.checkInDate}</td>
                        <td>${requestScope.singleReservation.checkOutDate}</td>
                    </tr>
                    </tbody>
                </table>
            </c:when>

            <c:when test="${requestScope.allReservations != null && requestScope.allReservations.size() > 0}">
                <table class="data-table w-full">
                    <thead>
                    <tr>
                        <th>Reservation #</th>
                        <th>Guest Name</th>
                        <th>Contact</th>
                        <th>Address</th>
                        <th>Room Type</th>
                        <th>Check-in</th>
                        <th>Check-out</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="res" items="${requestScope.allReservations}">
                        <tr>
                            <td>${res.reservationNumber}</td>
                            <td>${res.guestName}</td>
                            <td>${res.contactNumber}</td>
                            <td class="max-w-xs truncate">${res.address}</td>
                            <td>${res.roomType}</td>
                            <td>${res.checkInDate}</td>
                            <td>${res.checkOutDate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>

            <c:otherwise>
                <div class="text-center py-12 text-gray-400">
                    <i class="fas fa-inbox text-5xl mb-4 opacity-50"></i>
                    <p class="text-lg">No reservations found</p>
                    <p class="mt-2">Try searching with a different number or click Show All.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>