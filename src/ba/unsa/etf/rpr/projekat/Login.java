package ba.unsa.etf.rpr.projekat;

public class Login implements Validation {
    private String username, password;
    private UserType userType;
    private int id;
    private Person person;

    public Login(int id, String username, String password, UserType userType, Person person) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.id = id;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
