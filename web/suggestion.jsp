<%-- 
    Document   : suggestion
    Created on : 21 Nov, 2023, 8:08:52 PM
    Author     : sayan
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ page import="newproject.entities.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suggestion Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }
        
        #back-to-home {
            position: absolute;
            top: 10px;
            right: 10px;
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }
        
        h1 {
            color: #333;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        hr {
            border: 1px solid #ddd;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    
    <a href="index.jsp" id="back-to-home">Back to Home</a>
    
    <h1>Feedback Section</h1>
    <form action="SuggestionServlet" method="post">
        <input type="hidden" name="section" value="feedback">
        <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
            Your Name: <input type="text" name="username" value="${sessionScope.loggedInUser.username}" required><br>
        </c:when>
        <c:otherwise>
            Your Name: <input type="text" name="username" value="" required><br>
        </c:otherwise>
    </c:choose>


        Feedback: <textarea name="message" required></textarea><br>
        <input type="submit" value="Submit Feedback">
    </form>

    <h1>Complaints Section</h1>
    <form action="SuggestionServlet" method="post">
        <input type="hidden" name="section" value="complaints">
       <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
            Your Name: <input type="text" name="username" value="${sessionScope.loggedInUser.username}" required><br>
        </c:when>
        <c:otherwise>
            Your Name: <input type="text" name="username" value="" required><br>
        </c:otherwise>
    </c:choose>

        Complaint: <textarea name="message" required></textarea><br>
        <input type="submit" value="Submit Complaint">
    </form>

    <hr>


<h2>All Feedbacks:</h2>
<ul>
    <c:forEach var="message" items="${feedbackList}">
        <li>${message.username}: ${message.message}</li>
    </c:forEach>
</ul>

<h2>All Complaints:</h2>
<ul>
    <c:forEach var="message" items="${complaintList}">
        <li>${message.username}: ${message.message}</li>
    </c:forEach>
</ul>


</body>
</html>

