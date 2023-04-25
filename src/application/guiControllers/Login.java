package application.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.DatabaseConnection;
import application.Main;
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

public class Login {

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
    private TextField tUser;

    Alert error = new Alert(Alert.AlertType.ERROR);

    @FXML
    void loginUser() throws IOException {
        String user = tUser.getText();
        String passwd = tPasswd.getText();

        if (user.isBlank() && passwd.isBlank()) {
            error.setTitle("Error");
            error.setHeaderText("Los campos están vacios");
            error.showAndWait();
        } else {
            validarUsuario();
        }
    }

    public static int uuid;

    public void validarUsuario() throws IOException {
        String user = tUser.getText();
        String passwd = application.funciones.Cifrado.setCifrado(tPasswd.getText());
        System.out.println(passwd);
        
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT COUNT(1) FROM usuarios WHERE usuario = '" + user + "' AND passwd = '" + passwd + "';";
        String getUUID = "SELECT uuid FROM usuarios WHERE usuario = '" + user + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            Statement statUUID = connectDB.createStatement();
            ResultSet queryGetUUID = statUUID.executeQuery(getUUID);

            while (queryGetUUID.next()) {
                uuid = queryGetUUID.getInt("uuid");
                System.out.println("Login uuid: " + uuid);
            }

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Biblioteca.fxml"));
                    Main.cambiarEscena(root);

                } else {
                    error.setTitle("Error");
                    error.setHeaderText("Usuario o contraseña incorrectos");
                    error.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Register.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void initialize() {

    }

}
