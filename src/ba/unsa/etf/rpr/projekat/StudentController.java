package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class StudentController {
    private static Stage stage;
    public Label lblWelcome;
    public TableView<Grade> tableGrades;
    public TableColumn<Grade, String> colCourseName;
    public TableColumn<Grade, String> colGrade;
    private Student student;
    private ObservableList<Grade> listGrades;

    public StudentController(Student student) {
        this.student = student;
        listGrades = FXCollections.observableArrayList(EIndexDAO.getInstance().getStudentGrades(student));
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StudentController.stage = stage;
    }

    @FXML
    public void initialize() {
        String oldText = lblWelcome.getText();
        lblWelcome.setText(oldText + ", " + student);
        tableGrades.setItems(listGrades);
        colCourseName.setCellValueFactory(c -> {
            return new SimpleStringProperty(c.getValue().getCourse().getName() + " (" + c.getValue().getCourse().getCode() + ")");
        });
        colGrade.setCellValueFactory(c -> {
            return new SimpleStringProperty(c.getValue().getGrade().toString());
        });
    }

    public void initUser(Student student) {
        this.student = student;
        listGrades = FXCollections.observableArrayList(EIndexDAO.getInstance().getStudentGrades(student));
    }

    public void logout(ActionEvent actionEvent) {
        LoginManager manager = new LoginManager(stage);
        manager.logout();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void openAbout(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/about.fxml"), bundle);
            //AboutController aboutController = new AboutController();
            //loader.setController(aboutController);
            root = loader.load();
            stage.setTitle("About");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            writeFile(file);
        }
    }

    public void writeFile(File file) {
        if (file == null) return;
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter(file));
            output.println(student + ":\n");
            for (Grade g : listGrades) {
                output.printf("%s:%s:%s:%d::::\n", g.getCourse(), g.getDate(), g.getGrade(), g.getPoints());
            }
            output.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error saving file");
            alert.setHeaderText("Error saving file!");
            alert.setContentText("The selected file does not exist or can't be accessed!");

            alert.showAndWait();
        }
    }

    public void changeLanguageBA(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("bs", "BA"));
        Stage stage = (Stage) lblWelcome.getScene().getWindow();

        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Student");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    public void changeLanguageEN(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("en", "US"));
        Stage stage = (Stage) lblWelcome.getScene().getWindow();

        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Student");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    public void printReport(ActionEvent actionEvent) {
        try {
            new PrintReport().showReport(EIndexDAO.getInstance().getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }
}
