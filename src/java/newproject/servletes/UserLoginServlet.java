package newproject.servletes;

import newproject.entities.LoginDetailsDAO;
import newproject.entities.User; // Assuming you have the User class in this package

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");

        HttpSession session = request.getSession();

        try {
            String userType = LoginDetailsDAO.getUserType(username, userpassword);

            if ("normal".equals(userType) || "admin".equals(userType)) {
                // If the user type is normal or admin, set the User object in the session
                User user = new User(); // Create a new User object
                user.setUsername(username);
                user.setUserType(userType);

                // Set the User object in the session
                session.setAttribute("loggedInUser", user);

                // Redirect to the appropriate page
                if ("normal".equals(userType)) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("admin.jsp");
                }
                return;
            } else {
                request.setAttribute("message", "Invalid credentials. Please try again.");
                request.setAttribute("messageType", "error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Log the exception for debugging
            request.setAttribute("message", "An error occurred. Please try again later.");
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
