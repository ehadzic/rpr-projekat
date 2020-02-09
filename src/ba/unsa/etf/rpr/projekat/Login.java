package ba.unsa.etf.rpr.projekat;

public class Login implements Validation {
    private String username, password;
    private UserType userType;
    private int id;

    public Login(String username, String password, UserType userType, int id) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (validateUsername(username)) {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
