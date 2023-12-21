package newproject.entities;

public class User {
    private String userfullname;
    private String username;
    private String useremail;
    private String userphone;
    private String userpassword;
    private String gender;
    private String userType;

    // Constructors
    public User() {
    }

    public User(String userfullname, String username, String useremail, String userphone, String userpassword, String gender, String userType) {
        this.userfullname = userfullname;
        this.username = username;
        this.useremail = useremail;
        this.userphone = userphone;
        this.userpassword = userpassword;
        this.gender = gender;
        this.userType = userType;
    }

    // Getters and Setters
    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Other methods if needed
}
