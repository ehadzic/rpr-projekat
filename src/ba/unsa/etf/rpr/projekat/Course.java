package ba.unsa.etf.rpr.projekat;

public class Course implements Validation {
    private String code;
    private String name;
    private int creditsECTS;
    private Professor professor;
    private int semester;
    private int id;

    public Course(int id, String code, String name, int creditsECTS, Professor professor, int semester) throws IllegalSemesterException {
        this.id = id;
        this.code = code;
        this.name = name;
        this.creditsECTS = creditsECTS;
        this.professor = professor;
        if (semester >= 0) {
            this.semester = semester;
        } else throw new IllegalSemesterException();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (isAlphanumeric(code)) {
            this.code = code;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
