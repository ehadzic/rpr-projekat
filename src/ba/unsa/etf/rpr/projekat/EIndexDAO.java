package ba.unsa.etf.rpr.projekat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class EIndexDAO {
    private static EIndexDAO instance;
    private static Connection connection;

    private PreparedStatement getPersonById, getStudent, getProfessor, getAdmin, getUsersLogin, getGradeStudentId, getCourseByID, getCoursesProfessor, getGradesCourses, updateGrade;

    private EIndexDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:eindex.db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            // something wrong with connection (part connection=DriverManager...
            e.printStackTrace();
        }
        try {
            stmt.execute("SELECT * FROM Student");
        } catch (SQLException e) {
            // if there's no database, regenrate
            regenerateDatabase();

            try {
                // testing again
                stmt.execute("SELECT * FROM Student");
            } catch (SQLException ex) {
                // something's really wrong
                ex.printStackTrace();
            }
        }

        try {
            getPersonById = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
            getStudent = connection.prepareStatement("SELECT id, firstName, lastName, email, address, jmbg, indexID FROM Person, Student WHERE person_id=id AND id=?");
            getProfessor = connection.prepareStatement("SELECT id, firstName, lastName, email, address, jmbg, title FROM Person, Professor WHERE person_id=id AND id=?");
            getAdmin = connection.prepareStatement("SELECT * FROM Person, Admin WHERE person_id=id AND id=?");
            getUsersLogin = connection.prepareStatement("SELECT id, username, password, userType, person_id FROM Login");
            getGradeStudentId = connection.prepareStatement("SELECT * FROM Grade WHERE student_id=?");
            getCourseByID = connection.prepareStatement("SELECT * FROM Course WHERE id=?");
            getCoursesProfessor = connection.prepareStatement("SELECT * FROM Course WHERE professor_id=?");
            getGradesCourses = connection.prepareStatement("SELECT * FROM Grade WHERE course_id=?");
            updateGrade = connection.prepareStatement("UPDATE Grade SET grade=?, date=? WHERE id=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static EIndexDAO getInstance() {
        if (instance == null) {
            instance = new EIndexDAO();
        }
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        EIndexDAO.connection = connection;
    }

    private static UserType getUserType(int typeInt) {
        switch (typeInt) {
            case 1:
                return UserType.PROFESSOR;
            case 2:
                return UserType.ADMIN;
            default:
                return UserType.STUDENT;
        }
    }

    private void regenerateDatabase() {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("eindex.db.sql"));
            String query = "";
            while (input.hasNext()) {
                String line = input.nextLine();
                query += line;
                if (query.charAt(query.length() - 1) == ';') {
                    try {
                        java.sql.Statement statement = connection.createStatement();
                        statement.execute(query);
                        query = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Login> getUsersLogin() {
        ArrayList<Login> result = new ArrayList<>();
        try {
            ResultSet res = getUsersLogin.executeQuery();
            while (res.next()) {
                Login login = getLoginFromResultSet(res);
                result.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Login getLoginFromResultSet(ResultSet res) {
        try {
            return new Login(res.getInt(1), res.getString(2), res.getString(3), getUserType(res.getInt(4)), getPersonById(res.getInt(5), getUserType(res.getInt(4))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Person getPersonById(int id, UserType userType) {
        switch (userType) {
            case ADMIN:
                return getAdminById(id);
            case PROFESSOR:
                return getProfessorById(id);
            default:
                return getStudentById(id);
        }
    }

    private Student getStudentById(int id) {
        try {
            getStudent.setInt(1, id);
            ResultSet rs = getStudent.executeQuery();
            while (rs.next()) {
                return new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Professor getProfessorById(int id) {
        try {
            getProfessor.setInt(1, id);
            ResultSet rs = getProfessor.executeQuery();
            while (rs.next()) {
                return new Professor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Admin getAdminById(int id) {
        try {
            getAdmin.setInt(1, id);
            ResultSet rs = getAdmin.executeQuery();
            while (rs.next()) {
                return new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Grade> getStudentGrades(Student student) {
        ArrayList<Grade> result = new ArrayList<>();
        try {
            getGradeStudentId.setInt(1, student.getId());
            ResultSet rs = getGradeStudentId.executeQuery();
            while (rs.next()) {
                Grade grade = getGradeStudentFromResultSet(rs, student);
                result.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Grade getGradeStudentFromResultSet(ResultSet rs, Student student) {
        LocalDate date = null;
        Grade grade = null;
        try {
            String dateString = rs.getString(4);
            String[] dateList = dateString.split("\\.");
            date = LocalDate.of(parseInt(dateList[2]), parseInt(dateList[1]), parseInt(dateList[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            grade = new Grade(rs.getInt(1), student, getCourseById(rs.getInt(3)), date, Grade.parseGrade(rs.getInt(5)), rs.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    private Course getCourseById(int id) {
        Course course = null;
        try {
            getCourseByID.setInt(1, id);
            ResultSet rs = getCourseByID.executeQuery();
            course = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), getProfessorById(rs.getInt(5)), rs.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalSemesterException e) {
            e.printStackTrace();
        }
        return course;
    }


    public ArrayList<Course> getCoursesProfessor(Professor professor) {
        ArrayList<Course> result = new ArrayList<>();
        try {
            getCoursesProfessor.setInt(1, professor.getId());
            ResultSet rs = getCoursesProfessor.executeQuery();
            while (rs.next()) {
                result.add(getCourseFromResultSet(rs, professor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Course getCourseFromResultSet(ResultSet rs, Professor professor) {
        // id, code, name, credits, professor, semester
        try {
            return new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), professor, rs.getInt(6));
        } catch (IllegalSemesterException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Grade> getGradesOnCourse(Course course) {
        ArrayList<Grade> result = new ArrayList<>();
        try {
            getGradesCourses.setInt(1, course.getId());
            ResultSet rs = getGradesCourses.executeQuery();
            while (rs.next()) {
                result.add(getGradeStudentFromResultSet(rs, getStudentById(rs.getInt(2))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateGrade(Grade g) {
        try {
            updateGrade.setInt(1, g.getGrade().getValue());
            updateGrade.setString(2, g.getDate().getDayOfMonth() + "." + g.getDate().getMonthValue() + "." + g.getDate().getYear());
            updateGrade.setInt(3, g.getId());
            updateGrade.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
