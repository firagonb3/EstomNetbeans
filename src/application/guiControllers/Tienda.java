package application.guiControllers;

import application.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.clases.Juegos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Tienda implements Initializable {

    @FXML
    private Button bntUser;

    @FXML
    private Button btnComunidad;

    @FXML
    private Button btnTienda;

    @FXML
    private Button btnBiblioteca;

    @FXML
    private Button btnComprarjuego;

    @FXML
    private Circle userIcon;

    @FXML
    private Label lTituloJuego;

    @FXML
    private Label lDescripcionJuego;

    @FXML
    private ImageView imgBanner;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TableView<Juegos> listaJuegos;

    @FXML
    private TableColumn<Juegos, String> tJuego;

    private Image userICO = Biblioteca.userICO;
    private int uuid = Login.uuid;
    private int idEstom = 0;

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
    void comprarJuego(ActionEvent event) throws SQLException {
        application.funciones.ComprarJuego.comprar(uuid, idEstom);
    }

    private String username = Biblioteca.username.toUpperCase();
    ObservableList<Juegos> juegosTienda = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bntUser.setText(username);
        userIcon.setFill(new ImagePattern(userICO));

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            ResultSet rs = connectDB.createStatement().executeQuery(
                    "select * from juegos;");

            while (rs.next()) {
                juegosTienda.add(new Juegos(rs.getInt("estomid"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("banner"),
                        rs.getString("logo")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        tJuego.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getsJuego()));
        listaJuegos.setItems(juegosTienda);

        listaJuegos.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Image imgBannerGame = new Image(listaJuegos.getSelectionModel().getSelectedItem().getsBanner());
                Image imgLogoGame = new Image(listaJuegos.getSelectionModel().getSelectedItem().getsLogo());
                lTituloJuego.setText(listaJuegos.getSelectionModel().getSelectedItem().getsJuego());
                lDescripcionJuego.setText(listaJuegos.getSelectionModel().getSelectedItem().getsDescripcion());
                idEstom = listaJuegos.getSelectionModel().getSelectedItem().getsEstomID();
                System.out.println(idEstom);
                btnComprarjuego.setVisible(true);
                imgBanner.setImage(imgBannerGame);
                imgLogo.setImage(imgLogoGame);
            }

        });

    }

}
