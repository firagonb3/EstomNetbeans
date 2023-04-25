package application.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bntLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField tPasswd;

    @FXML
    private PasswordField tPasswd2;

    @FXML
    private TextField tUser;

    Alert error = new Alert(Alert.AlertType.ERROR);

    @FXML
    void loginUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Login.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void addUser(ActionEvent event) throws IOException {

        String user = tUser.getText();
        String passwd1 = application.funciones.Cifrado.setCifrado(tPasswd.getText());
        String passwd2 = application.funciones.Cifrado.setCifrado(tPasswd2.getText());

        if (user.isBlank()) {
            error.setTitle("Error");
            error.setHeaderText("El usuario está vacio");
            error.showAndWait();
        } else {
            if (passwd1.isBlank() && passwd2.isBlank()) {
                error.setTitle("Error");
                error.setHeaderText("Las contraseñas están vacias");
                error.showAndWait();
            } else {
                if (passwd1.equals(passwd2)) {
                    try {
                        application.funciones.CreateUser.crear(user, passwd1, passwd2);

                    } catch (Exception e) {
                        error.setTitle("ERROR");
                        error.setHeaderText("Ese nombre de usario ya existe");
                        error.showAndWait();
                        System.out.println("no se ha cambiado");
                    }
                } else {
                    error.setTitle("Error");
                    error.setHeaderText("Las contraseñas no coinciden");
                    error.showAndWait();
                }
            }

        }

        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Login.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void initialize() {

    }

}
