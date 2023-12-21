/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject.entities;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;


public class Helper {
    public static String get10Words(String desc){
     String[] strs =   desc.split(" ");
     
     if(strs.length>10){
         
         String res="";
         for(int i = 0; i<10; i++) {
             res=res+strs[i]+" ";
         }
         return (res+"...");
         
     }else {
         
         return (desc+"...");
     }
    }
    
public static void getCounts(HttpSession session) {
        Integer userCount = 0; // Use Integer instead of int

        try (Connection connection = DriverManager.getConnection("your-jdbc-url", "username", "password");
             PreparedStatement userStatement = connection.prepareStatement("SELECT COUNT(*) FROM users");
             ResultSet userResultSet = userStatement.executeQuery()) {

            if (userResultSet.next()) {
                userCount = userResultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        // Store counts in session
        session.setAttribute("userCount", userCount);
    }
    
}
