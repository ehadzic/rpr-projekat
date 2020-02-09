package ba.unsa.etf.rpr.projekat;

public enum Grades {
    A(10),
    B(9),
    C(8),
    D(7),
    E(6),
    NOT_GRADED(5);

    private int value;

    Grades(int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
