package controllers;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import Ordenamientos.MezclaNatural;
import Ordenamientos.Polifase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import principal.ControladorEscenas;
import util.AscendingOrder;
import util.DescendingOrder;

public class PrincipalController implements Initializable {

    @FXML
    private Button btnArchivo;

    @FXML
    private TextField pathField;

    @FXML
    private ChoiceBox<String> algoritmoCB;

    @FXML
    private ChoiceBox<String> criterioCB;

    @FXML
    private Button btnOrdena;

    @FXML
    private Button btnDestino;

    @FXML
    private TextField pathDestinoField;

    @FXML
    private ProgressBar progressBar;

    private FileChooser fileChooser;

    DirectoryChooser directoryChooser;

    private File fileOrigen;
    private File directorioDestino;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Se inicializan los seleccionadores
        fileChooser = new FileChooser();
        directoryChooser = new DirectoryChooser();

        // Se configura el filechooser
        configureFileChooser(fileChooser);
        directoryChooser.setInitialDirectory(new File("src"));

        // Se desactiva la edicion de los pathfield
        pathField.setEditable(false);
        pathDestinoField.setEditable(false);

        // Se asignan los item de los choicebox
        algoritmoCB.getItems().addAll("Polifase", "Mezcla Natural", "Radix");
        criterioCB.getItems().addAll("Ascendente", "Descendente");

        // Se agregar un listener a los choicebox

        ChangeListener<String> cambios = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                verificarSeleccionados();
            }
        };

        algoritmoCB.getSelectionModel().selectedItemProperty().addListener(cambios);
        criterioCB.getSelectionModel().selectedItemProperty().addListener(cambios);
    }

    @FXML
    void handleArchivoAction(ActionEvent event) {
        fileOrigen = fileChooser.showOpenDialog(ControladorEscenas.getStage());

        if (fileOrigen != null) {
            // Manda a llamar al método
            pathField.setText(fileOrigen.getAbsolutePath());
            verificarSeleccionados();
        }
    }

    @FXML
    void handleDestinoAction(ActionEvent event) {
        directorioDestino = directoryChooser.showDialog(ControladorEscenas.getStage());

        if (directorioDestino != null) {
            // Manda a llamar al método
            pathDestinoField.setText(directorioDestino.getAbsolutePath());
            verificarSeleccionados();
        }
    }

    @FXML
    void handleOrdenaAction(ActionEvent event) {
        String algoritmoElegido, criterioElegido;
        Comparator<Integer> comparator;
        Alert alert = new Alert(AlertType.INFORMATION);

        // Configurando un dialogo
        alert.setTitle("Proceso Completo");
        alert.setHeaderText("El archivo se ha ordenado");
        alert.setContentText(null);

        algoritmoElegido = algoritmoCB.getSelectionModel().getSelectedItem();
        criterioElegido = criterioCB.getSelectionModel().getSelectedItem();

        if (criterioElegido.equals("Ascendente"))
            comparator = new AscendingOrder();
        else
            comparator = new DescendingOrder();

        // Polifase.ordenar(origen, 4);

        switch (algoritmoElegido) {
            case "Polifase":
                Polifase.ordenar(fileOrigen, 10, comparator);
                break;
            case "Mezcla Natural":
                MezclaNatural.ordenar(fileOrigen, comparator);
                break;
            case "Radix":
                break;
        }

        alert.showAndWait();
    }

    // Metodo para configurar el filechooser

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Elige un archivo");
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    }

    // Metodo para verificar que se hayan seleccionado las opciones

    private void verificarSeleccionados() {

        String rutaOrigen, rutaDestino;
        String algoritmoElegido, criterioElegido;

        rutaOrigen = pathField.getText();
        rutaDestino = pathDestinoField.getText();
        algoritmoElegido = algoritmoCB.getSelectionModel().getSelectedItem();
        criterioElegido = criterioCB.getSelectionModel().getSelectedItem();

        if (rutaOrigen != null && algoritmoElegido != null && rutaDestino != null && criterioElegido != null)
            if (!rutaOrigen.isEmpty() && !algoritmoElegido.isBlank() && !rutaDestino.isEmpty()
                    && !criterioElegido.isBlank())
                btnOrdena.disableProperty().set(false);
    }

}
