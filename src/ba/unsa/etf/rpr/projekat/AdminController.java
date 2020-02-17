package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminController {

    private static Stage stage;
    public TextField fldName;
    public TextField fldSurname;
    public TextField fldEmail;
    public TextField fldAddress;
    public TextField fldJMBG;
    public TextField fldTitle;
    public TextField fldIndex;
    public TextField fldUsername;
    public TextField fldDeleteJmbg;
    public PasswordField fldPassword;
    public ListView<Login> listUsers;
    private UserModel model;

    public AdminController(UserModel model) {
        this.model = model;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AdminController.stage = stage;
    }

    @FXML
    public void initialize() {
        listUsers.setItems(FXCollections.observableArrayList(model.getUserList()));
    }

    public void addUser(ActionEvent actionEvent) {
        if (fldIndex.getText().equals("") && !fldTitle.getText().equals("")) {
            Professor p = new Professor(EIndexDAO.getInstance().getNextPersonId(), fldName.getText(), fldSurname.getText(), fldEmail.getText(), fldAddress.getText(), fldJMBG.getText(), fldTitle.getText());
            p.setLogin(new Login(EIndexDAO.getInstance().getNextLoginId(), fldUsername.getText(), fldPassword.getText(), UserType.PROFESSOR, p));
            model.getUserList().add(p.getLogin());
            EIndexDAO.getInstance().addProfessor(p);
        } else if (!fldIndex.getText().equals("") && fldTitle.getText().equals("")) {
            Student s = new Student(EIndexDAO.getInstance().getNextPersonId(), fldName.getText(), fldSurname.getText(), fldEmail.getText(), fldAddress.getText(), fldJMBG.getText(), fldIndex.getText());
            s.setLogin(new Login(EIndexDAO.getInstance().getNextLoginId(), fldUsername.getText(), fldPassword.getText(), UserType.STUDENT, s));
            model.getUserList().add(s.getLogin());
            EIndexDAO.getInstance().addStudent(s);
        } else if (fldIndex.getText().equals("") && fldTitle.getText().equals("")) {
            Admin a = new Admin(EIndexDAO.getInstance().getNextPersonId(), fldName.getText(), fldSurname.getText(), fldEmail.getText(), fldAddress.getText(), fldJMBG.getText());
            a.setLogin(new Login(EIndexDAO.getInstance().getNextLoginId(), fldUsername.getText(), fldPassword.getText(), UserType.ADMIN, a));
            model.getUserList().add(a.getLogin());
            EIndexDAO.getInstance().addAdmin(a);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding user");
            alert.setHeaderText("Error adding user!");
            alert.setContentText("User must be student (index not empty, title empty), professor (title not empty, index empty) or admin (index and title empty)!");

            alert.showAndWait();
            return;
        }

        // refresh model
        model.refreshModel();
        listUsers.setItems(FXCollections.observableArrayList(model.getUserList()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User added sucessfully");
        alert.setHeaderText("Successfully added a user!");

        alert.showAndWait();
    }


    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void deleteUser(ActionEvent actionEvent) {
        // ukoliko ne postoji dati jmbg, nece se nista desiti
        EIndexDAO.getInstance().deletePersonByJmbg(fldDeleteJmbg.getText());
        // refresh model
        model.refreshModel();
        listUsers.setItems(FXCollections.observableArrayList(model.getUserList()));
    }

    public void about(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/about.fxml"), bundle);
            //AboutController aboutController = new AboutController();
            //loader.setController(aboutController);
            root = loader.load();
            stage.setTitle("About");
            stage.setScene(new Scene(root, PopupControl.USE_COMPUTED_SIZE, PopupControl.USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void changeLanguageBA(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("bs", "BA"));
        Stage stage = (Stage) fldEmail.getScene().getWindow();

        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
            stage.setTitle("Admin");
            stage.setScene(new Scene(root, PopupControl.USE_COMPUTED_SIZE, PopupControl.USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguageEN(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("en", "US"));
        Stage stage = (Stage) fldEmail.getScene().getWindow();

        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
            stage.setTitle("Admin");
            stage.setScene(new Scene(root, PopupControl.USE_COMPUTED_SIZE, PopupControl.USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        LoginManager manager = new LoginManager(stage);
        manager.logout();
    }
}
