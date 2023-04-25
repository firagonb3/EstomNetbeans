package application.guiControllers;

import application.DatabaseConnection;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.sql.Statement;

public class Usuario implements Initializable {

    @FXML
    private Button bntUser;

    @FXML
    private Button btnComunidad;

    @FXML
    private Button btnTienda;

    @FXML
    private Button btnBiblioteca;

    @FXML
    private Button btnDelUser;

    @FXML
    private Circle userIcon;

    @FXML
    private Circle userIconBIG;

    @FXML
    private Button btnCHimg;

    @FXML
    private Button btnCHnombre;

    @FXML
    private Button btnCHpasswd;

    @FXML
    private PasswordField tNewPasswd;

    @FXML
    private PasswordField tNewPasswd2;

    @FXML
    private TextField tNewUsername;

    @FXML
    private Label msgUser;

    private String username = Biblioteca.username;
    private int uuid = Login.uuid;

    private Image userICO = Biblioteca.userICO;

    Alert error = new Alert(Alert.AlertType.ERROR);
    Alert correcto = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    void irTienda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Tienda.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void irBiblioteca(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Biblioteca.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void irComunidad(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Comunidad.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void irUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Usuario.fxml"));
        Main.cambiarEscena(root);
    }

    @FXML
    void delUser(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String delUser = "DELETE FROM usuarios WHERE uuid='" + uuid + "';";
        String delFotoUser = "DELETE FROM fotouser WHERE uuid='" + uuid + "';";
        String delJuegosComprados = "DELETE FROM juegoscomprados WHERE uuid='" + uuid + "';";

        try {
            Statement statDelJuegoComprado = connectDB.createStatement();
            statDelJuegoComprado.executeUpdate(delFotoUser);
            statDelJuegoComprado.executeUpdate(delJuegosComprados);
            statDelJuegoComprado.executeUpdate(delUser);

            correcto.setTitle("Estom");
            correcto.setHeaderText("Usuario eliminado correctamente");
            correcto.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("/application/gui/Login.fxml"));
            Main.cambiarEscena(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cambiarFotoPerfil(ActionEvent event) throws IOException {

        Image userIMG = application.funciones.UpdateIMG.update();

        if (userIMG != null) {
            userIcon.setFill(new ImagePattern(userIMG));
            userIconBIG.setFill(new ImagePattern(userIMG));
        }

    }

    @FXML
    void cambiarNombre(ActionEvent event) throws IOException {

        if (tNewUsername.getText().isBlank()) {
            error.setTitle("ERROR");
            error.setHeaderText("Introduza un nombre de usuario");
            error.showAndWait();
            System.out.println("campo cambiar nombre vacio");
        } else {
            String newUsername = tNewUsername.getText();
            if (application.funciones.UpdateUsername.actualizarUsuario(uuid, newUsername)) {
                bntUser.setText(newUsername.toUpperCase());
                msgUser.setText("Hola, " + newUsername);
            }
        }

    }

    @FXML
    void cambiarPasswd(ActionEvent event) throws IOException {
        String pass1 = application.funciones.Cifrado.setCifrado(tNewPasswd.getText());
        String pass2 = application.funciones.Cifrado.setCifrado(tNewPasswd2.getText());

        if (tNewPasswd.getText().isBlank() || tNewPasswd2.getText().isBlank()) {
            error.setTitle("ERROR");
            error.setHeaderText("Introduza una contraseña");
            error.showAndWait();
            System.out.println("campo cambiar passwd vacio");
        } else {

            if (pass1.equals(pass2)) {
                application.funciones.UpdatePasswd.passwd(uuid, pass1);
            } else {
                error.setTitle("ERROR");
                error.setHeaderText("Las contraseñas no coinciden");
                error.showAndWait();
                System.out.println("no coincide passwd pass1= " + tNewPasswd.getText() + " pass2= " + tNewPasswd.getText());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bntUser.setText(username.toUpperCase());
        msgUser.setText(msgUser.getText() + username);
        userIcon.setFill(new ImagePattern(userICO));
        userIconBIG.setFill(new ImagePattern(userICO));
        System.out.println("Perfil uuid: " + uuid);

    }

}
