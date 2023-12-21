package newproject.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final String JDBC_URL = "jdbc:oracle:thin:@LAPTOP-B1A57DKB:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "database";

    public static List<Category> getCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM category";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int categoryId = resultSet.getInt("CATEGORYID");
                            String categoryTitle = resultSet.getString("CATEGORYTITLE");
                            String categoryDescription = resultSet.getString("CATEGORYDESCRIPTION");

                            Category category = new Category(categoryId, categoryTitle, categoryDescription);
                            categories.add(category);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., log or show an error message
        }

        return categories;
    }

    public static boolean addCategory(String categoryTitle, String categoryDescription) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO CATEGORY (CATEGORYTITLE, CATEGORYDESCRIPTION) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, categoryTitle);
                    statement.setString(2, categoryDescription);

                    int rowsAffected = statement.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., log or show an error message
            return false;
        }
    }
}