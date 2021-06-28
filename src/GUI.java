import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import principal.ControladorEscenas;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ControladorEscenas controlador = new ControladorEscenas();
        controlador.setStage(primaryStage);

        primaryStage.setTitle("Ordenamiento Externo");
        // primaryStage.getIcons().add(new Image("images/pig.png"));
        ControladorEscenas.nuevaEscena(getClass().getResource("/views/principalView.fxml"));
    }
}
