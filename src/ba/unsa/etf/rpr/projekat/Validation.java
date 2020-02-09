package ba.unsa.etf.rpr.projekat;

public interface Validation {
    public static final int JMBG_LENGTH = 13;

    default boolean validateEmail(String email) {
        if (email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return true;
        }
        return false;
    }

    default boolean validateName(String name) {
        if (name.matches("^[a-zA-Z- ]*$"))
            return true;
        return false;
    }

    default boolean validateJMBG(String jmbg) {
        if (jmbg.length() == JMBG_LENGTH && jmbg.matches("^[a-zA-Z0-9]+$")) {
            return true;
        }
        return false;
    }
}
