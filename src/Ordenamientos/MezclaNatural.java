package Ordenamientos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import util.DescendingOrder;

public class MezclaNatural extends OrdenamientoExterno {
    private static File archivo1, archivo2, archivo0;

    private static Comparator<Integer> mComparator;

    public static void ordenar(File origen) {
        OrdenamientoExterno.setComparator(new DescendingOrder());
        int iteracion = 1;
        mComparator = OrdenamientoExterno.GetComparator();

        try {
            File currentDir = new File(".");
            String direccion = currentDir.getCanonicalPath() + File.separator + "src/files/";

            archivo1 = new File(direccion + "Archivo1-Iteracion" + iteracion + ".txt");
            archivo2 = new File(direccion + "Archivo2-Iteracion" + iteracion + ".txt");
            iteracion++;

            lecturaInicial(origen, archivo1, archivo2);

            do {
                archivo0 = new File(direccion + "Archivo0-Iteracion" + iteracion + ".txt");

                intercalar(archivo1, archivo2, archivo0);
                if (finalizo(archivo0))
                    break;
                archivo1 = new File(direccion + "Archivo1-Iteracion" + iteracion + ".txt");
                archivo2 = new File(direccion + "Archivo2-Iteracion" + iteracion + ".txt");

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
                    } else if (mComparator.compare(valorActual, valorAnterior) > 0)
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
