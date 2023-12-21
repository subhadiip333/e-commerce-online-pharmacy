package newproject.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDetailsDAO {
    private static final String JDBC_URL = "jdbc:oracle:thin:@LAPTOP-B1A57DKB:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "database";

    // Existing method to validate user credentials
    public static boolean validateUser(String username, String userpassword) throws SQLException {
        boolean isValid = false;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM RegistrationDetails WHERE USERNAME = ? AND USERPASSWORD = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, userpassword);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        isValid = resultSet.next();
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions, e.g., log or show an error message
            e.printStackTrace();
        }

        return isValid;
    }

    // New method to retrieve USER_TYPE based on username and password
    public static String getUserType(String username, String userpassword) throws SQLException {
        String userType = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT USER_TYPE FROM RegistrationDetails WHERE USERNAME = ? AND USERPASSWORD = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, userpassword);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            userType = resultSet.getString("USER_TYPE");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions, e.g., log or show an error message
            e.printStackTrace();
        }

        return userType;
    }
}
