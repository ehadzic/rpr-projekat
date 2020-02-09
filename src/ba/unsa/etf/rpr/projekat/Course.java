package ba.unsa.etf.rpr.projekat;

public class Course implements Validation {
    private String id;
    private String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
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
}
