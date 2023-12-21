package newproject.servletes;

import newproject.entities.ProductDAO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("pname");
        String productDescription = request.getParameter("pdesc");
        double productPrice = Double.parseDouble(request.getParameter("pprice"));
        double productDiscount = Integer.parseInt(request.getParameter("pdiscount"));
        int productQuantity = Integer.parseInt(request.getParameter("pquantity"));
        int categoryId = Integer.parseInt(request.getParameter("selectedCategory"));

        HttpSession session = request.getSession();

        try {
            // Save the product to the database
            int productId = ProductDAO.addProduct(productName, productDescription, productPrice,
                    productDiscount, productQuantity, categoryId);

            // Save the product image to the specified directory
            Part part = request.getPart("ppic");
            String fileName = "product_" + productId + "_" + getFileName(part);
            String uploadDir = "C:\\Users\\sayan\\OneDrive\\Documents\\NetBeansProjects\\Newproject\\web\\img\\product";

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            Path filePath = Paths.get(uploadDir, fileName);
            try (InputStream input = part.getInputStream()) {
                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Update the product with the image file name
            ProductDAO.updateProductImage(productId, fileName);

            // Set success message
            session.setAttribute("message", "Product added successfully!");
            session.setAttribute("messageType", "success");

            // Redirect to admin.jsp
            response.sendRedirect(request.getContextPath() + "/admin.jsp");
        } catch (SQLException ex) {
            // Handle database errors
            ex.printStackTrace();
            
            // Set error message
            session.setAttribute("message", "Failed to add product. Please try again.");
            session.setAttribute("messageType", "error");

            // Redirect to admin.jsp
            response.sendRedirect(request.getContextPath() + "/admin.jsp");
        }
    }

    // Extracts file name from HTTP header content-disposition
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
