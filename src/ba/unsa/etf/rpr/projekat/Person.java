package ba.unsa.etf.rpr.projekat;

public abstract class Person implements Validation {
    private String firstName, lastName, email, address, jmbg;
    private int id;
    private Login login;

    public Person(int id, String firstName, String lastName, String email, String address, String jmbg) {
        //if (validateJMBG(jmbg) && validateEmail(email) && validateName(firstName) && validateName(lastName) && isAlphanumeric(address)) {
        this.jmbg = jmbg;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.id = id;
        // } else throw new IllegalArgumentException("Wrong attribute value!");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (validateName(firstName)) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (validateName(lastName)) {
            this.lastName = lastName;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (isAlphanumeric(address)) {
            this.address = address;
        }
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        if (validateJMBG(jmbg)) {
            this.jmbg = jmbg;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
