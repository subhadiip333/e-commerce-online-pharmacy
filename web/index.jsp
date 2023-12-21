<%@page import="newproject.entities.Helper"%>
<%@page import="newproject.entities.CategoryDAO"%>
<%@page import="newproject.entities.Category"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@ page import="newproject.entities.ProductDAO" %>
<%@ page import="newproject.entities.Product" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <script src="script.js"></script>
     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <style>
        
        .add-to-cart-btn {
    background-color:whitesmoke;  /* Set the background color to white smoke */
}

.add-to-cart-btn:hover {
    background-color: #28a745; /* Change background color on hover */
    /* Add any additional styling for hover */
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
     
     <div class="container-fluid container-custom-margin">
         
    
   
   <div class="row mt-3 mx-2">
       
           <%-- Retrieve the list of products from the database --%>
<%
    String cat = request.getParameter("category");

    List<Product> products;
    try {
        if (cat == null || cat.trim().equals("") || cat.trim().equals("all")) {
            products = ProductDAO.getProducts();
        } else {
            int cid = Integer.parseInt(cat.trim());
            // Call a method in ProductDAO to get products by category
            products = ProductDAO.getProductsByCategory(cid);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error retrieving products from the database", e);
    }
%>


       
       <div class="col-md-2">
           
                         <%-- Retrieve the list of categories from the database --%>
<%
    String selectedCategory = request.getParameter("category");

    List<Category> categories;
    try {
        categories = CategoryDAO.getCategories();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error retrieving categories from the database", e);
    }
%>

<div class="list-group mt-4">
    <a href="index.jsp?category=all" class="list-group-item list-group-item-action <%= (selectedCategory == null || selectedCategory.trim().equals("") || selectedCategory.trim().equals("all")) ? "active" : "" %>"> All Products </a>

    <%-- Display each category --%>
    <%
        for (Category category : categories) {
    %>
        <a href="index.jsp?category=<%= category.getCategoryId() %>" class="list-group-item list-group-item-action <%= (selectedCategory != null && selectedCategory.trim().equals(String.valueOf(category.getCategoryId()))) ? "active" : "" %>"><%= category.getCategoryTitle() %></a>
    <%
        }
    %>
</div>
 
       </div>
       
       <div class="col-md-10">
           
           <div class="row mt-4">
               <div class="col-md-12">
                   
                   <div class="card-columns">
                       
                                    <%-- Display each product --%>
                     <%
                         for (Product product : products) {
                     %>
                     
                     
                     <div class="card product-card">
                         
                         <div class="container text-center">
                             <img class="card-img-top m-2" style="max-height: 200px; max-width: 100%;width: auto;" src="img/product/<%= product.getProductImage() %>" alt="Card image">
                         </div>

                         <div class="card-body">
                             
                             <h5 class="card-title"><%= product.getProductName() %></h5>
                             <p class="card-text">
                                 <%= Helper.get10Words(product.getProductDescription()) %>   
                             </p>
                             
                         </div>
                             
                             <div class="card-footer text-center">
                                 
                                 <button class="add-to-cart-btn button btn custom-bg" onclick="add_to_cart(<%= product.getProductId() %>,'<%= product.getProductName() %>',<%= product.getPriceAfterApplyingDiscount()%>)">Add to cart</button>
                                 <button class="btn btn-outline-success text-white"> &#8377;<%= product.getPriceAfterApplyingDiscount() %>/-  <span class="text-secondary discount-label"> &#8377;<%= product.getProductPrice() %>, <%= product.getProductDiscount() %>% off </span></button>
                                 
                             </div>
                         
                     </div>
                    <%
                        }
                        
                        if (products.size() == 0) {
                                out.println("<h3>No items in this category</h3>");
                            }
                     %>
        
                       
                       
                       
                   </div>
                   
               </div>
           </div>
               
           </div>
           
     
       </div>
       
   </div>
   </div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%@include file="common_modals.jsp" %>
</body>
</html>
