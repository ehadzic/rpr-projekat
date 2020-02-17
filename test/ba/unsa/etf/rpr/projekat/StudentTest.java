package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    @Test
    void constructor() {
        Student s = new Student(5, "Emina", "Emina", "m@m.com", "Adresa", "123456789111", "1111");
        assertEquals(5, s.getId());
        assertEquals("Emina", s.getFirstName());
        assertEquals("Emina", s.getLastName());
        assertEquals("m@m.com", s.getEmail());
        assertEquals("Adresa", s.getAddress());
        assertEquals("123456789111", s.getJmbg());
        assertEquals("1111", s.getIndexID());
    }

    @Test
    void setter() {
        Student s = new Student(5, "Emina", "Emina", "m@m.com", "Adresa", "123456789111", "1111");
        s.setIndexID("2222");
        assertEquals("2222", s.getIndexID());
    }
}
