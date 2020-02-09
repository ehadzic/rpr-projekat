package ba.unsa.etf.rpr.projekat;

public class Student extends Person {
    private Semester semester;
    private Course course;

    public Student(int id, String firstName, String lastName, String email, String address, String jmbg, Semester semester, Course course) {
        super(id, firstName, lastName, email, address, jmbg);
        this.semester = semester;
        this.course = course;
    }


    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
