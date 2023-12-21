/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject.servletes;

import newproject.entities.Message;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuggestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection URL, username, and password (update these accordingly)
    private static final String DB_URL = "jdbc:oracle:thin:@LAPTOP-B1A57DKB:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "database";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String section = request.getParameter("section");
        String username = request.getParameter("username");
        String messageText = request.getParameter("message");

        // Save the feedback and complaints to the database
        saveToDatabase(section, username, messageText);

        // Retrieve feedback and complaints from the database
        List<Message> feedbackList = getFromDatabase("feedback");
        List<Message> complaintList = getFromDatabase("complaints");

        // Set attributes to be used in JSP
        request.setAttribute("feedbackList", feedbackList);
        request.setAttribute("complaintList", complaintList);

        // Forward the request to the suggestion.jsp page
        request.getRequestDispatcher("suggestion.jsp").forward(request, response);
    }

    private void saveToDatabase(String section, String username, String message) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO messages (section, username, message) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, section);
                statement.setString(2, username);
                statement.setString(3, message);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Message> getFromDatabase(String section) {
    List<Message> messages = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "SELECT username, message FROM messages WHERE section = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, section);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String message = resultSet.getString("message");
                    messages.add(new Message(username, message));
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}
}