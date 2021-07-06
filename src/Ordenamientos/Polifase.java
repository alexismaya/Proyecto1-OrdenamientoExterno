package Ordenamientos;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class Polifase extends OrdenamientoExterno {

    private static File archivo1, archivo2, archivo0;

    public static void ordenar(File origen, int size, Comparator<Integer> comparator) {

        // archivoOrdenamiento[0]->F0
        // archivoOrdenamiento[1]->F1
        // archivoOrdenamiento[2]->F2
        int iteracion = 1;

        OrdenamientoExterno.setSizeBlock(size);
        OrdenamientoExterno.setComparator(comparator);

        try {
            // directorio = new File(".");
            String direccion = directorio.getCanonicalPath() + File.separator;
            // System.out.println(direccion);

            archivo1 = new File(direccion + "Archivo1 Polifase Iteracion" + iteracion + ".txt");
            archivo2 = new File(direccion + "Archivo2 Polifase Iteracion" + iteracion + ".txt");
            iteracion++;

            lecturaInicial(origen, archivo1, archivo2);

            do {
                archivo0 = new File(direccion + "Archivo0 Polifase Iteracion" + iteracion + ".txt");
                intercalar(archivo1, archivo2, archivo0);
                if (finalizo(archivo0)) {
                    File finalArchivo = new File(direccion + "Archivo Ordenado Polifase.txt");
                    escrituraFinal(archivo0, finalArchivo);
                    break;
                }

                archivo1 = new File(direccion + "Archivo1 Polifase Iteracion" + iteracion + ".txt");
                archivo2 = new File(direccion + "Archivo2 Polifase Iteracion" + iteracion + ".txt");
                iteracion++;
                distribuir(archivo0, archivo1, archivo2);
            } while (true);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error " + e.getMessage());
        }

    }

}
