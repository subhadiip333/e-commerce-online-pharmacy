package newproject.entities;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String JDBC_URL = "jdbc:oracle:thin:@LAPTOP-B1A57DKB:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "database";

public static int addProduct(String productName, String productDescription, double productPrice,
                             double productDiscount, int productQuantity, int categoryId) throws SQLException {
    try {
        Class.forName("oracle.jdbc.OracleDriver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO PRODUCT (PRODUCTID, PRODUCTNAME, PRODUCTDESCRIPTION, PRODUCTPRICE, " +
             "PRODUCTDISCOUNT, PRODUCTQUANTITY, CATEGORYID) " +
             "VALUES (PRODUCT_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql, new String[]{"PRODUCTID"})) {
                statement.setString(1, productName);
                statement.setString(2, productDescription);
                statement.setDouble(3, productPrice);
                statement.setDouble(4, productDiscount);
                statement.setInt(5, productQuantity);
                statement.setInt(6, categoryId);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            return generatedKeys.getInt(1);
                        }
                    }
                }

                throw new SQLException("Inserting product failed, no ID obtained.");
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        // Handle exceptions, e.g., log or show an error message
        throw new SQLException("Error adding product to the database", e);
    }
}


    public static void updateProductImage(int productId, String fileName) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "UPDATE PRODUCT SET PPIC = ? WHERE PRODUCTID = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, fileName);
                    statement.setInt(2, productId);

                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., log or show an error message
            throw new SQLException("Error updating product image in the database", e);
        }
    }

    public static List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "SELECT * FROM PRODUCT";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int productId = resultSet.getInt("PRODUCTID");
                            String productName = resultSet.getString("PRODUCTNAME");
                            String productDescription = resultSet.getString("PRODUCTDESCRIPTION");
                            double productPrice = resultSet.getDouble("PRODUCTPRICE");
                            double productDiscount = resultSet.getInt("PRODUCTDISCOUNT");
                            int productQuantity = resultSet.getInt("PRODUCTQUANTITY");
                            int categoryId = resultSet.getInt("CATEGORYID");
                            String productImage = resultSet.getString("PPIC");

                            Product product = new Product(productId, productName, productDescription,
                                    productPrice, (int) productDiscount, productQuantity, categoryId, productImage);
                            products.add(product);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., log or show an error message
            throw new SQLException("Error retrieving products from the database", e);
        }

        return products;
    }
    
    public static List<Product> getProductsByCategory(int categoryId) throws SQLException {
    List<Product> products = new ArrayList<>();

    try {
        Class.forName("oracle.jdbc.OracleDriver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM PRODUCT WHERE CATEGORYID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, categoryId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("PRODUCTID");
                        String productName = resultSet.getString("PRODUCTNAME");
                        String productDescription = resultSet.getString("PRODUCTDESCRIPTION");
                        double productPrice = resultSet.getDouble("PRODUCTPRICE");
                        double productDiscount = resultSet.getInt("PRODUCTDISCOUNT");
                        int productQuantity = resultSet.getInt("PRODUCTQUANTITY");
                        String productImage = resultSet.getString("PPIC");

                        Product product = new Product(productId, productName, productDescription,
                                productPrice, (int)productDiscount, productQuantity, categoryId, productImage);
                        products.add(product);
                    }
                }
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error retrieving products by category from the database", e);
    }

    return products;
}

}
