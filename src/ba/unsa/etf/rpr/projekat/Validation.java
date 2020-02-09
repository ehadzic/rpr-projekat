package ba.unsa.etf.rpr.projekat;

public interface Validation {
    public static final int JMBG_LENGTH = 13;

    default boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    default boolean validateName(String name) {
        return name.matches("^[a-zA-Z- ]*$");
    }


    default boolean validateJMBG(String jmbg) {
        return (jmbg.length() == JMBG_LENGTH && jmbg.matches("^[a-zA-Z0-9]+$"));
    }

    default boolean isAlphanumeric(String string) {
        return string.matches("[a-zA-Z0-9]+$");
    }

    default boolean validateTitle(String title) {
        return title.matches("[a-zA-Z .]+$");
    }

    default boolean validateUsername(String username) {
        return username.matches("^[a-zA-Z][a-zA-Z0-9.,$;]+$");

    }

    default boolean validatePassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*");
    }
}
