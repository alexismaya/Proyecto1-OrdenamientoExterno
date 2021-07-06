package Ordenamientos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MezclaNatural extends OrdenamientoExterno {
    private static File archivo1, archivo2, archivo0;

    public static void ordenar(File origen, Comparator<Integer> comparator) {
        OrdenamientoExterno.setComparator(comparator);
        int iteracion = 1;

        try {
            // directorio = new File(".");
            // + File.separator + "src/files/"
            System.out.println(directorio.getAbsolutePath());
            String direccion = directorio.getCanonicalPath() + File.separator;

            archivo1 = new File(direccion + "Archivo1-MezclaNatural-Iteracion" + iteracion + ".txt");
            archivo2 = new File(direccion + "Archivo2-MezclaNatural-Iteracion" + iteracion + ".txt");
            iteracion++;

            lecturaInicial(origen, archivo1, archivo2);

            do {
                archivo0 = new File(direccion + "Archivo0-MezclaNatural-Iteracion" + iteracion + ".txt");

                intercalar(archivo1, archivo2, archivo0);
                if (finalizo(archivo0)) {
                    File finalArchivo = new File(direccion + "Archivo Ordenado Mezcla Natural.txt");
                    escrituraFinal(archivo0, finalArchivo);
                    break;
                }
                archivo1 = new File(direccion + "Archivo1-MezclaNatural-Iteracion" + iteracion + ".txt");
                archivo2 = new File(direccion + "Archivo2-MezclaNatural-Iteracion" + iteracion + ".txt");

                iteracion++;
                distribuir(archivo0, archivo1, archivo2);
            } while (true);

        } catch (IOException e) {
            System.out.println("Ha ocurrido un error " + e.getMessage());
        }
    }

    protected static void lecturaInicial(File origen, File destino1, File destino2) {
        Scanner scFile;
        FileWriter fWriter1, fWriter2, fWriter;
        int contadorAlternador = 0;
        int alternador;
        ArrayList<Integer> bloque = new ArrayList<>();
        Integer valorActual, valorAnterior;
        valorAnterior = null;

        try {
            scFile = new Scanner(origen).useDelimiter(",");
            fWriter1 = new FileWriter(destino1);
            fWriter2 = new FileWriter(destino2);
            valorActual = scFile.nextInt();

            do {
                alternador = (int) Math.pow(-1, contadorAlternador);

                if (alternador > 0)
                    fWriter = fWriter1;
                else
                    fWriter = fWriter2;

                while (true) {

                    if (valorAnterior == null) {
                        bloque.add(valorActual);
                    } else if (comparator.compare(valorActual, valorAnterior) > 0)
                        bloque.add(valorActual);
                    else {
                        valorAnterior = null;
                        break;
                    }

                    valorAnterior = valorActual;
                    if (scFile.hasNextInt())
                        valorActual = scFile.nextInt();
                    else
                        break;

                }

                for (Integer integer : bloque) {
                    fWriter.write(integer + ",");
                }
                fWriter.write("],");
                bloque.clear();

                contadorAlternador++;

            } while (scFile.hasNextInt());
            fWriter1.close();
            fWriter2.close();
            scFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se ha encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Un error ha ocurrido: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en la lectura: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("El scanner se ha cerrado de forma inesperada: " + e.getMessage());
        }
    }
}
