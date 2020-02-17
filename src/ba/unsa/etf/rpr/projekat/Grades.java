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

    public static boolean validateGrade(String grade) {
        for (Grades g : Grades.values()) {
            if (grade.equals(g.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        switch (value) {
            case 10:
                return "A (10)";
            case 9:
                return "B (9)";
            case 8:
                return "C (8)";
            case 7:
                return "D (7)";
            case 6:
                return "E (6)";
            default:
                return "NOT GRADED";
        }
    }
}
