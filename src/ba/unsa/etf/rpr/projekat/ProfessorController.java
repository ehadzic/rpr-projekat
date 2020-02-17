package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ProfessorController {
    private static Stage stage;
    public Label lblWelcome;
    public TableView<Grade> tableGrades;
    public TableColumn<Grade, String> colCourse;
    public TableColumn<Grade, String> colStudent;
    public TableColumn<Grade, String> colGrade;
    public TextField fldGrade;
    public DatePicker dateNewGrade;
    private Professor professor;
    private ObservableList<Grade> listGrades;
    private ArrayList<Course> professorCourses;

    public ProfessorController(Professor professor) {
        this.professor = professor;
        professorCourses = EIndexDAO.getInstance().getCoursesProfessor(professor);
        listGrades = FXCollections.observableArrayList();
        for (int i = 0; i < professorCourses.size(); i++) {
            listGrades.addAll(EIndexDAO.getInstance().getGradesOnCourse(professorCourses.get(i)));
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ProfessorController.stage = stage;
    }

    @FXML
    public void initialize() {
        String oldText = lblWelcome.getText();
        lblWelcome.setText(oldText + ", " + professor);
        tableGrades.setItems(listGrades);
        colStudent.setCellValueFactory(c -> {
            return new SimpleStringProperty(c.getValue().getStudent().toString());
        });
        colGrade.setCellValueFactory(c -> {
            return new SimpleStringProperty(c.getValue().getGrade().toString());
        });
        colCourse.setCellValueFactory(c -> {
            return new SimpleStringProperty(c.getValue().getCourse().toString());
        });

        tableGrades.getSelectionModel().selectedItemProperty().addListener((obs, oldGrade, newGrade) -> {
            if (newGrade != null) {
                fldGrade.setText(newGrade.getGrade().toString());
            }
        });

        tableGrades.getSelectionModel().select(0);

        fldGrade.textProperty().addListener((obs, oldGrade, newGrade) -> {
            if (!newGrade.isEmpty() && Grades.validateGrade(newGrade)) {
                fldGrade.getStyleClass().removeAll("fieldNotCorrect");
                fldGrade.getStyleClass().add("fieldCorrect");
            } else {
                fldGrade.getStyleClass().removeAll("fieldCorrect");
                fldGrade.getStyleClass().add("fieldNotCorrect");
            }
        });
    }

    public void logout(ActionEvent actionEvent) {
        LoginManager manager = new LoginManager(stage);
        manager.logout();
    }

    public void changeGrade(ActionEvent actionEvent) {
        if (Grades.validateGrade(fldGrade.getText())) {
            Grade g = tableGrades.getSelectionModel().getSelectedItem();
            Grades grade = Grade.parseGrade(parseInt(fldGrade.getText().replaceAll("[^0-9]", "")));
            LocalDate newDate = dateNewGrade.getValue();
            g.setDate(newDate);
            g.setGrade(grade);
            EIndexDAO.getInstance().updateGrade(g);
            refreshTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Grade changed succesfully");
            alert.setHeaderText("Successfully changed the grade!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error changing grade");
            alert.setHeaderText("Error while changing grade");
            alert.setContentText("Grades have to follow the format letter (number)!");

            alert.showAndWait();
        }
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void refreshTable() {
        tableGrades.getItems().removeAll();
        listGrades = FXCollections.observableArrayList();
        for (int i = 0; i < professorCourses.size(); i++) {
            listGrades.addAll(EIndexDAO.getInstance().getGradesOnCourse(professorCourses.get(i)));
        }
        tableGrades.getItems().setAll(listGrades);
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
            for (Grade g : listGrades) {
                output.printf("%s:%s:%s:%s:%d:::\n", g.getStudent(), g.getCourse(), g.getDate(), g.getGrade(), g.getPoints());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/professor.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(bundle.getString("professorapp"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    public void changeLanguageEN(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("en", "US"));
        Stage stage = (Stage) lblWelcome.getScene().getWindow();

        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/professor.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(bundle.getString("professorapp"));
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }
}
