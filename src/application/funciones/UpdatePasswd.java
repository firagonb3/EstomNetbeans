package application.funciones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import application.DatabaseConnection;
import application.guiControllers.Biblioteca;
import javafx.scene.control.Alert;

public class UpdatePasswd {

    static Alert error = new Alert(Alert.AlertType.ERROR);
    static Alert correcto = new Alert(Alert.AlertType.INFORMATION);

    public static void passwd(int uuid, String newUsername) throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updateUsername = "UPDATE usuarios SET passwd = ? WHERE uuid = ?";

        try {
            PreparedStatement ps = connectDB.prepareStatement(updateUsername);
            ps.setString(1, newUsername);
            ps.setInt(2, uuid);

            if (ps.executeUpdate() == 1) {
                correcto.setTitle("Estom");
                correcto.setHeaderText("Se ha cambiado su contraseña");
                correcto.showAndWait();
                System.out.println("passwd cambiado");

                Biblioteca.username = newUsername;

            } else {
                error.setTitle("ERROR");
                error.setHeaderText("Error al modificar su contraseña");
                error.showAndWait();
                System.out.println("no se ha cambiado passwd");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
