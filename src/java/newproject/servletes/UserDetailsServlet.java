/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject.servletes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import newproject.entities.RegistrationDetailsDAO;
import newproject.entities.UserDetails;

/**
 *
 * @author sayan
 */
public class UserDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        System.out.println("Username: " + username);
        UserDetails userDetails = null;
        try {
            userDetails = RegistrationDetailsDAO.getUserDetails(username);
        } catch (SQLException ex) {
            Logger.getLogger(UserDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (userDetails != null) {
            System.out.println("UserDetails retrieved: " + userDetails);
            request.setAttribute("userDetails", userDetails);
            request.getRequestDispatcher("/normal.jsp").forward(request, response);
        } else {
            System.out.println("User not found for username: " + username);
            response.getWriter().println("User not found for username: " + username);
        }
    }
        
}
