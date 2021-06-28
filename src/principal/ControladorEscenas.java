package principal;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorEscenas {
    private static Stage stage;
    private static Object object;

    public static void nuevaEscena(URL url) {
        try {
            Scene newScene; // then we create a new scene with our new layout
            newScene = new Scene(FXMLLoader.load(url));
            stage.setScene(newScene);

            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setStage(Stage stageSent) {
        stage = stageSent;
    }

    public static Stage getStage() {
        return stage;
    }

    public void enviarDatos(Object objectSent) {
        if (objectSent != null)
            object = objectSent;
    }

    public Object recibirDatos() {
        return object;
    }

    public void clearDatos() {
        object = null;
    }
}
