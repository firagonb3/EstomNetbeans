package application.funciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.DatabaseConnection;
import application.guiControllers.Login;
import javafx.scene.image.Image;

public class GetUser {

    public static int uuid = Login.uuid;
    public static String username;
    public static Image userICO;

    public static String get() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getUsername = "SELECT usuario FROM usuarios WHERE uuid = " + uuid + ";";

        try {
            Statement statUsername = connectDB.createStatement();
            ResultSet queryGetUsername = statUsername.executeQuery(getUsername);

            while (queryGetUsername.next()) {
                username = queryGetUsername.getString("usuario");
                System.out.println("USER: " + username);
                return username;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
