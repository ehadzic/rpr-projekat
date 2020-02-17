package ba.unsa.etf.rpr.projekat;

public class Student extends Person {
    private String indexID;

    public Student(int id, String firstName, String lastName, String email, String address, String jmbg, String indexID) {
        super(id, firstName, lastName, email, address, jmbg);
        this.indexID = indexID;
    }

    public String getIndexID() {
        return indexID;
    }

    public void setIndexID(String indexID) {
        this.indexID = indexID;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + indexID + ")";
    }
}
