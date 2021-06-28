package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import principal.ControladorEscenas;

public class PrincipalController implements Initializable {

    @FXML
    private Button btnArchivo;

    @FXML
    private TextField pathField;

    @FXML
    private ChoiceBox<String> algoritmoCB;

    @FXML
    private Button btnOrdena;

    private FileChooser fileChooser;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        pathField.setEditable(false);
        algoritmoCB.getItems().addAll("Polifase", "Mezcla Directa", "Radix");

        algoritmoCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String oldAlgoritmo, String newAlgoritmo) {
                verificarSeleccionados();
            }
        });
    }

    @FXML
    void handleArchivoAction(ActionEvent event) {
        File file = fileChooser.showOpenDialog(ControladorEscenas.getStage());

        if (file != null) {
            // Manda a llamar al método
            pathField.setText(file.getAbsolutePath());
            verificarSeleccionados();
        }
    }

    @FXML
    void handleOrdenaAction(ActionEvent event) {

    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Elige un archivo");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    }

    private void verificarSeleccionados() {
        if (pathField.getText() != null && algoritmoCB.getSelectionModel().getSelectedItem() != null)
            if (!pathField.getText().isEmpty() && !algoritmoCB.getSelectionModel().getSelectedItem().isBlank())
                btnOrdena.disableProperty().set(false);
    }

}
