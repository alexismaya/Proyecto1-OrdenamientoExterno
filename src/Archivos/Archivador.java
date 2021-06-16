package Archivos;

import java.io.File;
import java.io.IOException;

public class Archivador {

    private String nombreArchivo;
    public File archivo;

    public Archivador(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        cargarArchivo();
    }

    // Metodo para crear el archivo

    private void crearArchivo() {
        try {
            if (archivo.createNewFile())
                System.out.println("Archivo creado");
        } catch (IOException e) {
            System.out.println("No se ha podido crear el archivo.");
        }
    }

    // Metodo cargar archivo

    private void cargarArchivo() {
        archivo = new File(nombreArchivo + ".txt");

        if (!archivo.exists())
            crearArchivo();
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

}
