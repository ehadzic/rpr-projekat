package ba.unsa.etf.rpr.projekat;

public class IllegalSemesterException extends Exception {

    public IllegalSemesterException() {
        super("Semester number can't be negative or zero!");
    }
}
