<%@ page import="newproject.entities.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Navigation Menu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="nav.css">
    <!-- Boxicons CDN Link -->
<!--    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<nav>
    <div class="navbar">
        <i class='bx bx-menu'></i>
        <div class="logo"><a href="index.jsp">Health Buddy <i class="fa fa-plus" aria-hidden="true"></i></a></div>
        <div class="nav-links">
            <div class="sidebar-logo">
                <span class="logo-name">Health Buddy</span>
                <i class='bx bx-x'></i>
            </div>
            <ul class="links">
                <li><a href="index.jsp">HOME</a></li>
<!--                <li>
                    <a href="#">Categories</a>
                    <i class='bx bxs-chevron-down htmlcss-arrow arrow'></i>
                    <ul class="htmlCss-sub-menu sub-menu">
                         Add your category links here 
                    </ul>
                </li>-->
                <li>
                    <a href="#" data-toggle="modal" data-target="#cart">
                        <i class="fa fa-cart-plus" style="font-size: 20px;"></i> <span class="ml-0 cart-items">(0)</span>
                    </a>
                </li>
                
                
                 <!-- Check if the user is not null, then show user-specific links -->
                <c:if test="${empty sessionScope.loggedInUser}">
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                </c:if>

                <c:if test="${not empty sessionScope.loggedInUser}">
    <c:choose>
        <c:when test="${sessionScope.loggedInUser.getUserType() eq 'normal'}">
            <li><a href="normal.jsp">${sessionScope.loggedInUser.username}</a></li>
        </c:when>
        <c:when test="${sessionScope.loggedInUser.getUserType() eq 'admin'}">
            <li><a href="admin.jsp">${sessionScope.loggedInUser.username}</a></li>
        </c:when>
        <c:otherwise>
            <!-- Handle other roles or provide a default action -->
        </c:otherwise>
    </c:choose>
    <li><a href="LogoutServlet">Logout</a></li>
</c:if>

                <li><a href="aboutUs.jsp">ABOUT US</a></li>
                <li><a href="suggestion.jsp">SUGGESTION</a></li>
            </ul>
        </div>
    </div>
</nav>
<script src="nav.js"></script>
</body>
</html>
