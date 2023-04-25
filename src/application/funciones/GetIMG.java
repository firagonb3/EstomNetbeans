package application.funciones;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.DatabaseConnection;
import application.guiControllers.Login;
import javafx.scene.image.Image;

public class GetIMG {

    public static int uuid = Login.uuid;
    public static String username;
    public static Image userICO;

    public static InputStream get() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getIMG = "SELECT img FROM fotouser WHERE uuid =" + uuid + ";";

        try {
            Statement statIMG = connectDB.createStatement();
            ResultSet queryGetIMG = statIMG.executeQuery(getIMG);

            if (queryGetIMG.next()) {
                Blob foto = queryGetIMG.getBlob(1);
                InputStream inputStream = foto.getBinaryStream();
                return inputStream;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
