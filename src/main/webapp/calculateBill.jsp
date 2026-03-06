<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculate Bill - Ocean View Resort</title>
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
        <h2>Calculate Bill</h2>
    </div>
    <p class="sub-text">Enter reservation number to generate bill.</p>

    <c:if test="${not empty requestScope.errorMessage}">
        <div class="error-message">
            <c:out value="${requestScope.errorMessage}"/>
        </div>
    </c:if>

    <div class="form-container">
        <form action="calculateBill" method="get" class="flex flex-col sm:flex-row gap-4 items-end">
            <div class="flex-grow">
                <label for="reservationNumber" class="form-label block mb-2">Reservation Number</label>
                <input type="number" id="reservationNumber" name="reservationNumber"
                       class="form-input w-full h-12" required placeholder="Enter reservation number"
                       value="${param.reservationNumber}" min="1">
            </div>
            <button type="submit" class="form-button w-96">
                <i class="fas fa-calculator"></i> Generate Bill
            </button>
        </form>
    </div>

    <c:if test="${not empty requestScope.reservation}">
        <div class="bill-container">
            <div class="flex justify-between items-start border-b pb-6 mb-8">
                <div>
                    <h3 class="bill-title text-4xl font-bold">Ocean View Resort</h3>
                    <p class="text-gray-600 mt-1">Galle, Sri Lanka</p>
                </div>
                <div class="text-right">
                    <p class="text-sm text-gray-500">Date</p>
                    <p class="font-medium"><fmt:formatDate value="<%= new java.util.Date() %>" pattern="dd MMMM yyyy"/></p>
                </div>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-8 mb-8">
                <div>
                    <h4 class="font-semibold text-lg mb-3 text-blue-700">Guest Details</h4>
                    <p><strong>Name:</strong> ${requestScope.reservation.guestName}</p>
                    <p><strong>Address:</strong> ${requestScope.reservation.address}</p>
                    <p><strong>Contact:</strong> ${requestScope.reservation.contactNumber}</p>
                </div><br>
                <div>
                    <h4 class="font-semibold text-lg mb-3 text-blue-700">Booking Details</h4>
                    <p><strong>Reservation #:</strong> ${requestScope.reservation.reservationNumber}</p>
                    <p><strong>Check-in:</strong> ${requestScope.reservation.checkInDate}</p>
                    <p><strong>Check-out:</strong> ${requestScope.reservation.checkOutDate}</p>
                </div>
            </div>

            <table class="bill-table w-full">
                <thead>
                <tr>
                    <th class="text-left">Room Type</th>
                    <th class="text-right">Rate per Night</th>
                    <th class="text-right">Nights</th>
                    <th class="text-right">Amount (LKR)</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${requestScope.reservation.roomType}</td>
                    <td class="text-right">Rs. ${requestScope.rate}</td>
                    <td class="text-right">${requestScope.nights}</td>
                    <td class="text-right font-semibold">Rs. ${requestScope.total}</td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="total-row">
                    <td colspan="3" class="text-right text-xl font-bold">TOTAL AMOUNT</td>
                    <td class="text-right text-2xl font-bold text-green-700">Rs. ${requestScope.total}</td>
                </tr>
                </tfoot>
            </table>

            <div class="text-center mt-10 pt-6 border-t text-gray-600">
                Thank you for choosing Ocean View Resort.<br>
                We look forward to welcoming you again soon!
            </div>
        </div>

        <div class="print-button-wrapper">
            <button onclick="window.print()"
                    class="form-button">
                <i class="fas fa-print text-xl"></i>
                Print Bill
            </button>
        </div>
    </c:if>
</div>
</body>
</html>