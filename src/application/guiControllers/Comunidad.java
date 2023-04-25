package application.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Comunidad implements Initializable {

    @FXML
    private Button bntUser;

    @FXML
    private Button btnComunidad;

    @FXML
    private Button btnTienda;

    @FXML
    private Button btnBiblioteca;
    
    @FXML
    private WebView estomWEB;

    @FXML
    private Circle userIcon;

    private Image userICO = Biblioteca.userICO;

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

    private String username = Biblioteca.username.toUpperCase();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        WebEngine webEngine = estomWEB.getEngine();

        webEngine.load("https://estom.wolfiex.com/noticias");
        bntUser.setText(username);
        userIcon.setFill(new ImagePattern(userICO));

    }

}
