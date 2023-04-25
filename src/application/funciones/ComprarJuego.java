/*
 */
package application.funciones;

import application.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Manel
 */
public class ComprarJuego {

    static Alert error = new Alert(Alert.AlertType.ERROR);
    static Alert correcto = new Alert(Alert.AlertType.INFORMATION);

    public static void comprar(int uuid, int estomID) {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String addUser = "INSERT INTO juegoscomprados values (?,?)";

            PreparedStatement ps = connectDB.prepareStatement(addUser);

            ps.setInt(1, uuid);
            ps.setInt(2, estomID);

            if (ps.executeUpdate() == 1) {
                correcto.setTitle("Estom");
                correcto.setHeaderText("El juego se ha comprado correctamente");
                correcto.showAndWait();
                System.out.println("juego comprado");
            }
        } catch (SQLException ex) {
            error.setTitle("ERROR");
            error.setHeaderText("Error al comprar el juego o ya lo tienes");
            error.showAndWait();
            System.out.println("juego no comprado");
        }

    }
}
