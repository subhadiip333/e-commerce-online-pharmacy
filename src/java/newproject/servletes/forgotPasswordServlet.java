package newproject.servletes;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import newproject.entities.User;

public class forgotPasswordServlet extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String errorMessage = null;

        if (isEmailExistsInDatabase(email)) {
            // Generate OTP
            int otpValue = generateOTP();

            // Send OTP Email
            boolean emailSent = sendOTPEmail(email, otpValue);

            if (emailSent) {
                // Store OTP and email in session for verification on the next page
                HttpSession session = request.getSession();
                session.setAttribute("otp", otpValue);
                session.setAttribute("email", email);

                // Forward to the page where the user will enter the OTP
                response.sendRedirect("EnterOtp.jsp");
                return;
            } else {
                errorMessage = "Failed to send OTP. Please try again.";
            }
        } else {
            errorMessage = "Email not found in our database";
        }

        // If there is an error, forward to the same page with an error message
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }

    private boolean isEmailExistsInDatabase(String email) {
        // Assuming you have a list of users or some mechanism to retrieve user data
        List<User> userList = getUsersFromDatabase(); // Replace with your actual logic to get user data

        // Check if the entered email exists in the list of users
        for (User user : userList) {
            if (email.equals(user.getUseremail())) {
                return true; // Email exists in the database
            }
        }

        return false; // Email not found in the database
    }

    // Replace this method with your actual logic to retrieve user data from the database
    // Replace this method with your actual logic to retrieve user data from the database
// Replace this method with your actual logic to retrieve user data from the database
private List<User> getUsersFromDatabase() {
    List<User> userList = new ArrayList<>();

    // Define your database connection details
    String jdbcUrl = "jdbc:oracle:thin:@LAPTOP-B1A57DKB:1521:orcl";
    String dbUser = "system";
    String dbPassword = "database";

    try {
        Class.forName("oracle.jdbc.OracleDriver");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String sql = "SELECT USERFULLNAME, USERNAME, USEREMAIL, USERPHONE, USERPASSWORD, GENDER FROM RegistrationDetails";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String userfullname = resultSet.getString("USERFULLNAME");
                        String username = resultSet.getString("USERNAME");
                        String useremail = resultSet.getString("USEREMAIL");
                        String userphone = resultSet.getString("USERPHONE");
                        String userpassword = resultSet.getString("USERPASSWORD");
                        String gender = resultSet.getString("GENDER");

                        User user = new User();
                        user.setUserfullname(userfullname);
                        user.setUsername(username);
                        user.setUseremail(useremail);
                        user.setUserphone(userphone);
                        user.setUserpassword(userpassword);
                        user.setGender(gender);

                        userList.add(user);
                    }
                }
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        // Handle the exception based on your application's requirements
    }

    return userList;
}



    private int generateOTP() {
        // Generate a random 6-digit OTP
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    private boolean sendOTPEmail(String userEmail, int otpValue) {
         // Configure mail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");


        // Gmail account credentials
        final String username = "technok3j@gmail.com"; // Replace with your Gmail email
        final String password = "zfbmvqzfqgqjczzq"; // Replace with your Gmail app password

        // Get the session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Compose the message
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Forgot Password - OTP");
            message.setText("Your OTP for password reset is: " + otpValue);

            // Send the message
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
