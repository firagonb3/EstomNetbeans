package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("gui/Login.fxml"));
            scene = new Scene(root);

            String css = this.getClass().getResource("gui/application.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo-icon.png")));

            stage.setTitle("Estom");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambiarEscena(Parent root) {
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
