package ba.unsa.etf.rpr.projekat;

public class Course implements Validation {
    private String id;
    private String name;
    private int creditsECTS;
    private Professor professor;
    private int semester;

    public Course(String id, String name, int creditsECTS, Professor professor, int semester) throws IllegalSemesterException {
        this.id = id;
        this.name = name;
        this.creditsECTS = creditsECTS;
        this.professor = professor;
        if (semester >= 0) {
            this.semester = semester;
        } else throw new IllegalSemesterException();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (isAlphanumeric(id)) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isAlphanumeric(name)) {
            this.name = name;
        }
    }

    public int getCreditsECTS() {
        return creditsECTS;
    }

    public void setCreditsECTS(int creditsECTS) {
        this.creditsECTS = creditsECTS;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
