package application.guiControllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.DatabaseConnection;
import application.Main;
import application.clases.Juegos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Biblioteca implements Initializable {

    @FXML
    private Button bntUser;

    @FXML
    private Button btnComunidad;

    @FXML
    private Button btnTienda;

    @FXML
    private Button btnBiblioteca;

    @FXML
    private Button bntPlay;

    @FXML
    private Circle userIcon;

    @FXML
    private Label lTituloJuego;

    @FXML
    private Label lDescripcionJuego;

    @FXML
    private Label lEspacio;

    @FXML
    private ImageView imgBanner;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TableView<Juegos> listaJuegos;

    @FXML
    private TableColumn<Juegos, String> tJuego;

    @FXML
    private Label lActulizacion1;

    @FXML
    private Label lActulizacion2;

    @FXML
    private Label lTactulizacion;

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
    void jugar(ActionEvent event) {
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Error al jugar");
        errorDialog.setHeaderText("Tu ordenador no cumple los requisitos minimos");
        errorDialog.showAndWait();
    }

    public static String username;
    public static Image userICO;
    public static int uuid = Login.uuid;
    public static double dAlmacenamiento;

    ObservableList<Juegos> juegos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        username = application.funciones.GetUser.get();
        bntUser.setText(username.toUpperCase());

        InputStream getIMG = application.funciones.GetIMG.get();

        if (getIMG != null) {
            userICO = new Image(getIMG);
            userIcon.setFill(new ImagePattern(userICO));
        } else {
            userICO = new Image(getClass().getResource("/application/img/default-user.png").toExternalForm());
            userIcon.setFill(new ImagePattern(userICO));
        }

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            ResultSet rs = connectDB.createStatement().executeQuery(
                    "select nombre, almacenamiento, banner, logo from juegos j, juegoscomprados jc, usuarios u where jc.uuid = u.uuid and jc.estomid = j.estomid and u.uuid = "
                    + uuid + ";");

            while (rs.next()) {
                juegos.add(new Juegos(rs.getString("nombre"), rs.getDouble("almacenamiento"), rs.getString("banner"),
                        rs.getString("logo")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tJuego.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getsJuego()));
        listaJuegos.setItems(juegos);

        listaJuegos.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Image imgBannerGame = new Image(listaJuegos.getSelectionModel().getSelectedItem().getsBanner());
                Image imgLogoGame = new Image(listaJuegos.getSelectionModel().getSelectedItem().getsLogo());
                dAlmacenamiento = listaJuegos.getSelectionModel().getSelectedItem().getsAlmacenamiento();
                bntPlay.setVisible(true);
                lActulizacion1.setVisible(true);
                lActulizacion2.setVisible(true);
                lTactulizacion.setVisible(true);
                lEspacio.setText("Espacio necesario: " + dAlmacenamiento + " GB");
                imgBanner.setImage(imgBannerGame);
                imgLogo.setImage(imgLogoGame);
            }

        });

    }

}
