package application.funciones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import application.DatabaseConnection;
import application.guiControllers.Biblioteca;
import javafx.scene.control.Alert;

public class UpdateUsername {

    static Alert error = new Alert(Alert.AlertType.ERROR);
    static Alert correcto = new Alert(Alert.AlertType.INFORMATION);

    public static boolean actualizarUsuario(int uuid, String newUsername) throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updateUsername = "UPDATE usuarios SET usuario = ? WHERE uuid = ?";

        try {
            PreparedStatement ps = connectDB.prepareStatement(updateUsername);
            ps.setString(1, newUsername);
            ps.setInt(2, uuid);

            if (ps.executeUpdate() == 1) {
                correcto.setTitle("Estom");
                correcto.setHeaderText("Se ha cambiado su nombre de usuario");
                correcto.showAndWait();
                System.out.println("nombre cambiado");
                Biblioteca.username = newUsername;
                return true;

            }

        } catch (Exception e) {
            error.setTitle("ERROR");
                error.setHeaderText("Error al modificar su nombre de usuario o ya existe");
                error.showAndWait();
                System.out.println("no se ha cambiado");
        }
        return false;
    }

}
