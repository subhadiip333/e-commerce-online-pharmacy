

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="newproject.entities.UserDetails" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="newproject.entities.RegistrationDetailsDAO" %>

<%
    String username = request.getParameter("username"); // Assuming the username is passed as a parameter
    UserDetails userDetails = null;

    try {
        userDetails = RegistrationDetailsDAO.getUserDetails(username);
    } catch (SQLException e) {
        e.printStackTrace();
        response.getWriter().println("Error retrieving user details: " + e.getMessage());
    }
%>

<html>
<head>
    <title>User Details Page</title>
</head>
<body>
    <h2>User Details</h2>

    <% if (userDetails != null) { %>
        <p>User Full Name: <%= userDetails.getUserfullname() %></p>
        <p>Email: <%= userDetails.getUseremail() %></p>
        <p>Phone: <%= userDetails.getUserphone() %></p>
        <p>Password: <%= userDetails.getUserpassword() %></p>
        <p>Gender: <%= userDetails.getGender() %></p>
    <% } else { %>
        <p>User not found for username: <%= username %></p>
    <% } %>
</body>
</html>
