package newproject.servletes;

import newproject.entities.RegistrationDetailsDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sayan
 */
public class UserRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userfullname = request.getParameter("userfullname");
        String username = request.getParameter("username");
        String useremail = request.getParameter("useremail");
        String userphone = request.getParameter("userphone");
        String userpassword = request.getParameter("userpassword");
        String gender = request.getParameter("gender"); // Added gender parameter

        try {
            RegistrationDetailsDAO.insertUser(userfullname, username, useremail, userphone, userpassword, gender); // Updated insertUser method
            request.setAttribute("message", "Registration successful!");
            request.setAttribute("messageType", "success");
        } catch (Exception e) {
            request.setAttribute("message", "Registration failed. Please try again.");
            request.setAttribute("messageType", "error");
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}