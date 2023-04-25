package application.funciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.DatabaseConnection;
import application.guiControllers.Login;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class UpdateIMG {

    private static int uuid = Login.uuid;

    static Alert error = new Alert(Alert.AlertType.ERROR);
    static Alert correcto = new Alert(Alert.AlertType.INFORMATION);

    public static Image update() {
        FileChooser elegirFoto = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Imágenes (*.png) (*.jpg) (*.jpeg) (*.bmp) (*.wbmp) (*.gif)", "*.png", "*.jpg", "*.jpeg", "*.bmp",
                "*.wbmp", "*.gif");
        elegirFoto.getExtensionFilters().add(extFilter);
        File selectedFile = elegirFoto.showOpenDialog(null);

        if (selectedFile != null) {
            try {

                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                String insertImg = "INSERT INTO fotouser VALUES (?,?);";
                String updateIMG = "UPDATE fotouser SET img = ? WHERE uuid = ?";
                String chkIMG = "SELECT COUNT(1) FROM fotouser WHERE uuid = '" + uuid + "';";

                Statement statIMG = connectDB.createStatement();
                ResultSet queryGetCountUUID = statIMG.executeQuery(chkIMG);

                boolean isImage = false;

                while (queryGetCountUUID.next()) {
                    if (queryGetCountUUID.getInt(1) == 1) {
                        isImage = true;
                    } else {
                        isImage = false;
                    }
                }

                if (isImage) {
                    PreparedStatement ps = connectDB.prepareStatement(updateIMG);
                    InputStream inpsIMG = new FileInputStream(selectedFile);

                    ps.setBlob(1, inpsIMG);
                    ps.setInt(2, uuid);

                    if (ps.executeUpdate() == 1) {
                        correcto.setTitle("Estom");
                        correcto.setHeaderText("Se ha modificado su imágen de peril");
                        correcto.showAndWait();
                        System.out.println("se ha modificado");
                    } else {
                        error.setTitle("ERROR");
                        error.setHeaderText("Error al modificar su imágen de peril");
                        error.showAndWait();
                        System.out.println("no se ha modificado");
                    }

                } else {
                    PreparedStatement ps = connectDB.prepareStatement(insertImg);
                    InputStream inpsIMG = new FileInputStream(selectedFile);
                    ps.setInt(1, uuid);
                    ps.setBlob(2, inpsIMG);

                    if (ps.executeUpdate() == 1) {
                        correcto.setTitle("Estom");
                        correcto.setHeaderText("Se ha añadido una nueva imágen de peril");
                        correcto.showAndWait();
                        System.out.println("se ha insertado");
                    } else {
                        error.setTitle("ERROR");
                        error.setHeaderText("Error al añadir una nueva imágen de peril");
                        error.showAndWait();
                        System.out.println("no se ha insertado");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                return new Image(new FileInputStream(selectedFile));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            error.setTitle("ERROR");
            error.setHeaderText("No se ha elegido ningún archivo");
            error.showAndWait();
            System.out.println("no se ha elegido");
            return null;
        }
        return null;
    }

    public void msgCorrecto() {
        correcto.setTitle("Estom");
        correcto.setHeaderText("Operación completada con exito");
        correcto.showAndWait();
    }

}
