package newproject.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDetailsDAO {
    private static final String JDBC_URL = "jdbc:oracle:thin:@LAPTOP-B1A57DKB:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "database";
    
    
        public static void insertUser(String userfullname, String username, String useremail, String userphone, String userpassword, String gender) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");



            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO RegistrationDetails (USERFULLNAME, USERNAME, USEREMAIL, USERPHONE, USERPASSWORD, GENDER) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, userfullname);
                    statement.setString(2, username);
                    statement.setString(3, useremail);
                    statement.setString(4, userphone);
                    statement.setString(5, userpassword);
                    statement.setString(6, gender);
                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions, e.g., log or show an error message
            e.printStackTrace();
        }
    } 
         public static UserDetails getUserDetails(String username) throws SQLException {
        UserDetails userDetails = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT USERFULLNAME, USERNAME, USEREMAIL, USERPHONE, USERPASSWORD, GENDER FROM RegistrationDetails WHERE USERNAME = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String userfullname = resultSet.getString("USERFULLNAME");
                            String useremail = resultSet.getString("USEREMAIL");
                            String userphone = resultSet.getString("USERPHONE");
                            String userpassword = resultSet.getString("USERPASSWORD");
                            String gender = resultSet.getString("GENDER");

                            userDetails = new UserDetails(userfullname, username, useremail, userphone, userpassword, gender);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userDetails;
    }
}
