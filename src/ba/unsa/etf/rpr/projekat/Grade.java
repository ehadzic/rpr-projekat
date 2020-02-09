package ba.unsa.etf.rpr.projekat;

import java.time.LocalDate;

public class Grade {
    private int id;
    private Student student;
    private Course course;
    private LocalDate date;
    private Grades grade;


    public Grade(int id, Student student, Course course, LocalDate date, Grades grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.date = date;
        this.grade = grade;
    }

    public static Grade newGrade(int id, Student student, Course course, Grades grade) {
        Grade result = new Grade(id, student, course, null, grade);
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
}
