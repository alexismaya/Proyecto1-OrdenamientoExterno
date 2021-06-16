package Ordenamientos;

import java.util.ArrayList;
import Archivos.ArchivoOrdenamiento;

public class Polifase extends OrdenamientoExterno {

    @Override
    public void ordenar() {

        ArrayList<Integer> bloque = archivoOrdenamiento[0].generarBloque();
        bloque = ordenamientoInterno(bloque);

    }

    private void init(int blockSize) {
        numeroArchivos = 4;
        archivoOrdenamiento = new ArchivoOrdenamiento[numeroArchivos];
        int i = 0;

        for (ArchivoOrdenamiento archivo : archivoOrdenamiento) {
            archivo = new ArchivoOrdenamiento("F" + i);
            archivo.setBlockSize(blockSize);
            i++;
        }
    }

}
