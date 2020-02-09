package ba.unsa.etf.rpr.projekat;

public enum UserType {
    STUDENT(0),
    PROFESSOR(1),
    ADMIN(2);

    private int value;

    UserType(int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
