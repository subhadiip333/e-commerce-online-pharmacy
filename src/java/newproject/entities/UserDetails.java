/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newproject.entities;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetails {
    private String userfullname;
    private String useremail;
    private String userphone;
    private String userpassword;
    private String gender;

    // Constructor to create UserDetails from ResultSet
    public UserDetails(ResultSet resultSet) throws SQLException {
        this.userfullname = resultSet.getString("USERFULLNAME");
        this.useremail = resultSet.getString("USEREMAIL");
        this.userphone = resultSet.getString("USERPHONE");
        this.userpassword = resultSet.getString("USERPASSWORD");
        this.gender = resultSet.getString("GENDER");
    }

    UserDetails(String userfullname, String username, String useremail, String userphone, String userpassword, String gender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getter methods for fields
    public String getUserfullname() {
        return userfullname;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public String getGender() {
        return gender;
    }

    public UserDetails(String userfullname, String useremail, String userphone, String userpassword, String gender) {
        this.userfullname = userfullname;
        this.useremail = useremail;
        this.userphone = userphone;
        this.userpassword = userpassword;
        this.gender = gender;
    }
    
}

