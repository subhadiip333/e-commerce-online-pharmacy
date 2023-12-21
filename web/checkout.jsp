<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="newproject.entities.User" %>

<%
    // Retrieve the user from the session
    User user = (User) session.getAttribute("loggedInUser");

    // Check if the user is logged in
    if (user == null) {
        request.setAttribute("message", "You are not logged in. Please log in first to access the checkout page");
        request.setAttribute("messageType", "error");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>


<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <script src="script.js"></script>
     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
     <style> .container-custom-margin {
        margin-top: 90px; 
    }</style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
  
        <div class="container container-custom-margin">
            <div class="row mt-5">
                <div class="col-md-6">  
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center mb-5">Your selected products</h3>
                            <div class="cart-body">
                                
                            </div>
                            
                        </div>
                    </div>
                </div>
                <div class="col-md-6">  
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center mb-5">Your details for order</h3>
                            <form action="#">
                                
                                    <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                
                                 <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter your name">
                    </div>

                    <div class="form-group">
                        <label for="contactNumber">Contact Number</label>
                        <input type="text" class="form-control" id="contactNumber" aria-describedby="emailHelp" placeholder="Enter your contact number">
                    </div>

                                
                                            <div class="form-group">
                <label for="exampleFormControlTextarea1">Your Shipping Address</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" placeholder="Enter your address" rows="3"></textarea>
              </div>
                                <div class="container text-center">
                                    <button class="btn btn-outline-success">Order Now</button>
                                    <button class="btn btn-outline-primary">Continue Shopping</button>
                                </div>   
                            </form>
                            
                        </div>
                    </div>
                
                
                </div>  
                 
            </div>      
        </div>
        
        
        
        
        
       
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<%@include file="common_modals.jsp" %>
</body>
</html>
