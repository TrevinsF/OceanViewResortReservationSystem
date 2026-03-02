<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Ocean View Resort</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body class="login-page">

<%
    if (session.getAttribute("currentUser") != null && request.getParameter("loginSuccess") == null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<div class="login-container">
    <h2 class="login-title">Ocean View Resort Reservation System</h2>
    <h3 class="login-subtitle">Login</h3>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <% if (request.getParameter("logout") != null) { %>
    <p class="success-message">Logged out successfully.</p>
    <% } %>

    <% if (request.getParameter("loginSuccess") != null) { %>
    <p class="success-message">Login successful!</p>
    <script>
        setTimeout(() => {
            window.location.href = "dashboard.jsp";
        }, 1200);
    </script>
    <% } else { %>

    <form action="login" method="post">
        <div class="form-group">
            <input type="text" id="username" name="username" placeholder="Username" class="form-input" required>
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" placeholder="Password" class="form-input" required>
        </div>
        <button type="submit" class="form-button">Login</button>
    </form>

    <% } %>
</div>

</body>
</html>