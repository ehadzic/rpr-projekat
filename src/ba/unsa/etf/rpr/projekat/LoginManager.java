package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginManager {
    private static Stage stage;

    public LoginManager(Stage stage) {
        this.stage = stage;
    }


    public void logout() {
        Parent root = null;
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"), bundle);
        try {
            root = loader.load();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
