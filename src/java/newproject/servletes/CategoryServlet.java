package newproject.servletes;

import newproject.entities.Category;
import newproject.entities.CategoryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CategoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryTitle = request.getParameter("categoryTitle");
        String categoryDescription = request.getParameter("categoryDescription");

        HttpSession session = request.getSession();

        try {
            // Add the new category
            boolean isSuccess = CategoryDAO.addCategory(categoryTitle, categoryDescription);

            if (isSuccess) {
                session.setAttribute("message", "Category added successfully!");
                session.setAttribute("messageType", "success");
            } else {
                session.setAttribute("message", "Failed to add category. Please try again.");
                session.setAttribute("messageType", "error");
            }

            // Reload categories after adding a new one
            List<Category> categories = CategoryDAO.getCategories();
            session.setAttribute("categories", categories);

        } catch (SQLException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Log the exception for debugging
            session.setAttribute("message", "An error occurred. Please try again later.");
            session.setAttribute("messageType", "error");
        }

        // Redirect to the same page
        response.sendRedirect(request.getContextPath() + "/admin.jsp");
    }
}
