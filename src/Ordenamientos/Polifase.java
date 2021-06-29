package Ordenamientos;

import java.io.File;
import java.io.IOException;
import util.DescendingOrder;

public class Polifase extends OrdenamientoExterno {

    private static File archivo1, archivo2;

    public static void ordenar(File origen, int size) {

        // archivoOrdenamiento[0]->F0
        // archivoOrdenamiento[1]->F1
        // archivoOrdenamiento[2]->F2

        OrdenamientoExterno.setSizeBlock(size);
        OrdenamientoExterno.setComparator(new DescendingOrder());

        try {
            File currentDir = new File(".");
            String direccion = currentDir.getCanonicalPath() + File.separator + "src/files/";
            // System.out.println(direccion);

            archivo1 = new File(direccion + "Archivo1.txt");
            archivo2 = new File(direccion + "Archivo2.txt");

            lecturaInicial(origen, archivo1, archivo2);

            do {
                intercalar(archivo1, archivo2, origen);
                if (finalizo(origen))
                    break;
                distribuir(origen, archivo1, archivo2);
            } while (true);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error " + e.getMessage());
        }

    }

}
