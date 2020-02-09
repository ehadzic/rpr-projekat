package ba.unsa.etf.rpr.projekat;

public class Student extends Person {
    private Course course;

    public Student(int id, String firstName, String lastName, String email, String address, String jmbg, Course course) {
        super(id, firstName, lastName, email, address, jmbg);
        this.course = course;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
