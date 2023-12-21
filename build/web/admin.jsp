<%@page import="java.util.List"%>
<%@page import="newproject.entities.Category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="newproject.entities.CategoryDAO" %>
<%@ page import="newproject.entities.Helper" %>
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Check if the user is logged in
    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) {
        request.setAttribute("message", "You are not logged in. Please log in first.");
        request.setAttribute("messageType", "error");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
        String userType = user.getUserType();

        // Check if the user is an admin
        if (userType == null || !"admin".equals(userType)) {
            request.setAttribute("message", "You are not authorized to access this page. Please log in as an admin.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
%>


<%
    // Call the getCounts method and pass the session object
    Helper.getCounts(request.getSession());

    // Retrieve the user count from the session
    HttpSession sessioncount = request.getSession();
    Integer userCount = (Integer) sessioncount.getAttribute("userCount");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <script src="script.js"></script>
     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <style>
    .custom-heading {
    color: #007BFF; /* Set your desired text color */
    font-weight: bold;
    transition: color 0.3s; /* Smooth transition for color change on hover */
}
          
    .container-custom-margin {
        margin-top: 90px; /* Adjust the margin-top as needed */
    }
    
    
    .container-custom-margin {
        margin-top: 90px; /* Adjust the margin-top as needed */
    }
    .button{
        color: #007bff!important;
    }
/*    .list-group-item-active{
        background: #673ab7!important;
        border-color: #673ab7!important;
    }
    */
    .discount-label{
        font-size: 10px!important;
        font-style: italic!important;
    }        
    .product-card:hover{
        background: #e2e2e2;
        cursor: pointer;
    }
    
/*    Toast CSS*/
#toast {
    min-width: 300px;
    position: fixed;
    bottom: 30px;
    left: 50%;
    margin-left: -120px;
    background: #333;
    padding: 15px;
    color: white;
    text-align: center;
    z-index: 1;
    font-size: 18px;
    visibility: hidden;
    box-shadow: 0px 0px 100px #000;
}

#toast.display {
    visibility: visible;
    animation: fadeIn 0.5s, fadeOut 0.5s 2.5s;
}

@keyframes fadeIn {
    from {
        bottom: 0;
        opacity: 0;
    }
    to {
        bottom: 30px;
        opacity: 1;
    }
}

@keyframes fadeOut {
    from {
        bottom: 30px;
        opacity: 1;
    }   
    to {
        bottom: 0;
        opacity: 0;
    }    
}
</style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
       
        
        <div class="container container-custom-margin">
        <div class="row mt-3">
            <div class="col-md-4">
<div class="message <%= session.getAttribute("messageType") != null ? session.getAttribute("messageType") : "" %>">
    <%= session.getAttribute("message") != null ? session.getAttribute("message") : "" %>
</div>

                <div class="card">
                    <div class="card-body text-center">
                        <div class="img-container">
                            <img style="max-width: 125px;" class="img-fluid rounded-circle" src="" alt="" srcset="">
                        </div>
                        <h1><%= userCount %>2</h1>
                        <h1 class="custom-heading text-uppercase text-muted">Users</h1>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body text-center">
                        <div class="img-container">
                            <img style="max-width: 125px;" class="img-fluid rounded-circle" src="" alt="" srcset="">
                        </div>
                        <h1><c:out value="${fn:length(sessionScope.categories)}" /></h1>
                        <h1  class="custom-heading text-uppercase text-muted">Categories</h1>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body text-center">
                        <div class="img-container">
                            <img style="max-width: 125px;" class="img-fluid rounded-circle" src="" alt="" srcset="">
                        </div>
                        <h1>32</h1>
                        <h1  class="custom-heading text-uppercase text-muted">Products</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-6">
                <div class="card" data-toggle="modal" data-target="#add-category-modal">
                    <div class="card-body text-center">
                        <div class="img-container">
                            <img style="max-width: 125px;" class="img-fluid rounded-circle" src="" alt="" srcset="">
                        </div>
                        <h1 class="custom-heading text-uppercase text-muted">Add Category</h1>
                    </div>
                </div> 
            </div>

            <div class="col-md-6">
                <div class="card" data-toggle="modal" data-target="#add-product-modal">
                    <div class="card-body text-center">
                        <div class="img-container">
                            <img style="max-width: 125px;" class="img-fluid rounded-circle" src="" alt="" srcset="">
                        </div>
                        <h1 class="custom-heading text-uppercase text-muted">Add Products</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    

<!-- Modal -->
<div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Category Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <form action="CategoryServlet" method="post">
              <input type="hidden" name="operation" value="addcategory"/>
              <div class="form-group">
                  <input type="text" class="form-control" name="categoryTitle" placeholder="Enter category title" required />
              </div>
               <div class="form-group">
                   <textarea style="height: 250px;" class="form-control" placeholder="Enter category description" name="categoryDescription" required >
                       
                   </textarea>
               </div>
              <div class="container text-center">
                  <button class="btn btn-outline-success">Add Category</button>
                   <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
          </form>
      </div>
    </div>
  </div>
</div>



<!-- Modal -->
<div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Product Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <form action="AddProductServlet" method="post" enctype="multipart/form-data">
              <div class="form-group">
                  <input type="text" class="form-control" placeholder="Enter title of the product" name="pname" required />
              </div>
              
              <div class="form-group">
                  <textarea style="height: 180px;" class="form-control" placeholder="Enter product description" name="pdesc"> </textarea>
              </div>
              
              <div class="form-group">
                  <input type="number" class="form-control" placeholder="Enter price of the product" name="pprice" required />
              </div>
              
             <div class="form-group">
                  <input type="number" class="form-control" placeholder="Enter product discount" name="pdiscount" required />
             </div>
              
              <div class="form-group">
                  <input type="number" class="form-control" placeholder="Enter product quantity" name="pquantity" required />
              </div>
              
               <div class="form-group">
                  <label for="categoryDropdown">Select Category:</label>
                  <select class="form-control" id="categoryDropdown" name="selectedCategory">
                      <%-- Populate the dropdown with categories from the session --%>
                      <c:forEach var="category" items="${sessionScope.categories}" varStatus="loop">
                          <option value="${category.categoryId}" <c:if test="${loop.index == 0}">selected</c:if>>${category.categoryTitle}</option>
                      </c:forEach>
                  </select>
              </div>
              
              <div class="form-group">
                  <label for="ppic"> Select picture of the product </label>
                  <br>
                  <input type="file" id="ppic" name="ppic" required />
              </div>
              
              <div class="container text-center">
                  <button class="btn btn-outline-success"> Add Product </button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
          </form>
      </div>
    </div>
  </div>
</div>
                      
                      
                      
                      <%@include file="common_modals.jsp" %>    
    </body>
</html>
<%
        }
    }
%>
