

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOgin</title>
        <link rel="stylesheet" href="login.css">
    </head>
    <body>
        <div class="container">
	<div class="screen">
			<form class="login" action="UserLoginServlet" method="post">
                            <h2 class="login-heading">Log In Here</h2>
                            <div class="screen__content">
                      <div class="message <%= request.getAttribute("messageType") != null ? request.getAttribute("messageType") : "" %>">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </div>   
				<div class="login__field">
					<i class="login__icon fas fa-user"></i>
                                        <input type="text" class="login__input" placeholder="Username" required name="username">
				</div>
				<div class="login__field">
					<i class="login__icon fas fa-lock"></i>
                                        <input type="password" class="login__input" placeholder="Password" required name="userpassword">
				</div>
				<button class="button login__submit">
					<span class="button__text">Log In Now</span>
					<i class="button__icon fas fa-chevron-right"></i>
				</button>
			</form>
                         <div>
                 <a href="forgotPassword.jsp" class="forgotpassword"> Forgot Password? </a>
                 <a href="register.jsp" class="register-link">Not yet registered? Register here</a>
            </div>
		</div>
		<div class="screen__background">
<!--			<span class="screen__background__shape screen__background__shape4"></span>
			<span class="screen__background__shape screen__background__shape3"></span>		
			<span class="screen__background__shape screen__background__shape2"></span>
			<span class="screen__background__shape screen__background__shape1"></span>-->
		</div>		
	</div>
</div>
    </body>
</html>
