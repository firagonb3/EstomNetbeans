package application.funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.DatabaseConnection;
import javafx.scene.control.Alert;

public class CreateUser {

    static Alert error = new Alert(Alert.AlertType.ERROR);
    static Alert correcto = new Alert(Alert.AlertType.INFORMATION);

    public static void crear(String user, String passwd1, String passwd2) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String addUser = "INSERT INTO usuarios (usuario,passwd) values (?,?)";

        try {
            PreparedStatement ps = connectDB.prepareStatement(addUser);

            ps.setString(1, user);
            ps.setString(2, passwd1);

            if (ps.executeUpdate() == 1) {
                correcto.setTitle("Estom");
                correcto.setHeaderText("Usuario creado correctamente");
                correcto.showAndWait();
                System.out.println("usuario creado");
            }
        } catch (Exception e) {
            error.setTitle("ERROR");
            error.setHeaderText("Error al crear el usuario o ya existe");
            error.showAndWait();
            System.out.println("no se ha creado user");
        }

    }
}
