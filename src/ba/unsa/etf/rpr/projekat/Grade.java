package ba.unsa.etf.rpr.projekat;

import java.time.LocalDate;

import static ba.unsa.etf.rpr.projekat.Grades.*;

public class Grade {

    private int id;
    private Student student;
    private Course course;
    private LocalDate date;
    private Grades grade;
    private int points;

    public Grade(int id, Student student, Course course, LocalDate date, Grades grade, int points) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.date = date;
        this.grade = grade;
        this.setPoints(points);
    }


    public static Grade newGrade(int id, Student student, Course course, Grades grade, int points) {
        Grade result = new Grade(id, student, course, null, grade, points);
        result.setDate(LocalDate.now());
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Grades getGrade() {
        return grade;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points >= 0 && points <= 100) {
            this.points = points;
        }
    }

    public static Grades parseGrade(int anInt) {
        switch (anInt) {
            case 10:
                return A;
            case 9:
                return B;
            case 8:
                return C;
            case 7:
                return D;
            case 6:
                return E;
            default:
                return NOT_GRADED;
        }
    }

    public Grades generateGrade() {
        if (points < 55) grade = Grades.NOT_GRADED;
        else if (points >= 55 && points <= 64) grade = E;
        else if (points >= 55 && points <= 74) grade = D;
        else if (points >= 75 && points <= 84) grade = C;
        else if (points >= 85 && points <= 94) grade = Grades.B;
        else grade = Grades.A;

        return grade;
    }

    @Override
    public String toString() {
        return grade.toString();
    }
}
