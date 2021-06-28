package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import principal.ControladorEscenas;

public class PrincipalController implements Initializable {

    @FXML
    private Button btnArchivo;

    @FXML
    private TextField pathField;

    @FXML
    private ChoiceBox<?> AlgoritmoPicker;

    @FXML
    private Button btnOrdena;

    private FileChooser fileChooser;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Elige un archivo");

    }

    @FXML
    void handleArchivoAction(ActionEvent event) {
        fileChooser.showOpenDialog(ControladorEscenas.getStage());
    }

    @FXML
    void handleOrdenaAction(ActionEvent event) {

    }

}
