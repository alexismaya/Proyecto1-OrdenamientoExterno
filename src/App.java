import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Ordenamientos.MezclaNatural;
import Ordenamientos.OrdenamientoExterno;
import Ordenamientos.Polifase;
import util.AscendingOrder;
import util.DescendingOrder;

public class App {

    public static void main(String[] args) throws IOException {
        // System.out.println("Hello, World!");
        GUI.main(args);
        // File currentDir = new File(".");
        // String direccion = currentDir.getCanonicalPath() + File.separator +
        // "src/files/";

        // File origen = new File(direccion + "Archivo0.txt");
        // Polifase.ordenar(origen, 4,new DescendingOrder());
        // MezclaNatural.ordenar(origen, new DescendingOrder());

    }
}
