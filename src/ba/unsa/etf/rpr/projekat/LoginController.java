package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class LoginController {
    private static Stage stage;
    public TextField user;
    public PasswordField password;
    public Button loginButton;
    private Login login = null;
    private UserModel userModel;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        LoginController.stage = stage;
    }


    @FXML
    public void initialize() {
        userModel = new UserModel();
        user.textProperty().addListener((obs, oldName, newName) -> {
            if (!newName.isEmpty()) {
                user.getStyleClass().removeAll("fieldNotCorrect");
                user.getStyleClass().add("fieldCorrect");
            } else {
                user.getStyleClass().removeAll("fieldCorrect");
                user.getStyleClass().add("fieldNotCorrect");
            }
        });
    }

    public void login(ActionEvent actionEvent) {
        if (user.getText() == "" || password.getText() == "") {
            return;
        }
        String username = user.getText();
        String pass = password.getText();
        for (Login l : userModel.getUserList()) {
            if (username.equals(l.getUsername()) && pass.equals(l.getPassword())) {
                login = l;
                showMainView(login);
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login error");
        alert.setHeaderText("Username or password wrong!");

        alert.showAndWait();
    }


    private void showMainView(Login login) {
        FXMLLoader loader;
        switch (login.getUserType()) {
            case STUDENT:
                showStudentView();
                break;
            case PROFESSOR:
                showProfessorView();
                break;
            default:
                // admin

                break;
        }

    }

    private void showProfessorView() {
        ProfessorController tc = new ProfessorController((Professor) login.getPerson());
        tc.setStage(stage);

        // Load in the .fxml file:
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/professor.fxml"), bundle);
        loader.setController(tc);
        Parent root = null;
        try {
            root = loader.load();
            // Set the scene:
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(bundle.getString("professorapp"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        // Set the scene:
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Professor");
        stage.show();*/
    }

    private void showStudentView() {
        StudentController tc = new StudentController((Student) login.getPerson());
        tc.setStage(stage);

        // Load in the .fxml file:
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student.fxml"), bundle);
        loader.setController(tc);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the scene:
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Student");
        stage.show();
    }
}
