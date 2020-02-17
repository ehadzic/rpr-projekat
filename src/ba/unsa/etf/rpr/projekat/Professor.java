package ba.unsa.etf.rpr.projekat;

public class Professor extends Person {
    private String title;

    public Professor(int id, String firstName, String lastName, String email, String address, String jmbg, String title) {
        super(id, firstName, lastName, email, address, jmbg);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (validateTitle(title)) {
            this.title = title;
        }
    }

    @Override
    public String toString() {
        return title + " " + super.toString();
    }
}
