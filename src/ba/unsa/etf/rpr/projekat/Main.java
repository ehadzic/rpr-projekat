package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("en", "US"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"), bundle);
        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        LoginController.setStage(primaryStage);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
