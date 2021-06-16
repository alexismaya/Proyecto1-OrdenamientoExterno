package Ordenamientos;

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import Archivos.ArchivoOrdenamiento;

public abstract class OrdenamientoExterno {
    protected int numeroArchivos;
    protected ArchivoOrdenamiento[] archivoOrdenamiento;

    public ArrayList<Integer> ordenamientoInterno(ArrayList<Integer> bloque) {
        Collections.sort(bloque);
        return bloque;
    }

    abstract void ordenar();

    public static void intercalar(File archivo1, File archivo2, File destino) {

        Scanner scFile1, scFile2;
        Integer integer1, integer2;
        String valor1, valor2;
        int bloqueActual = 0;
        FileWriter fWriter;

        try {
            scFile1 = new Scanner(archivo1).useDelimiter(",");
            scFile2 = new Scanner(archivo2).useDelimiter(",");
            fWriter = new FileWriter(destino);
            while (scFile1.hasNext() || scFile2.hasNext()) {
                valor1 = scFile1.next();
                valor2 = scFile2.next();

                // Si se ha acabado de recorrer el bloque del primer documento
                if (valor1.equals("]")) {
                    do {
                        fWriter.write(valor2);
                        valor2 = scFile2.next();
                    } while (!valor2.equals("]"));
                    continue;
                }

                // Si se ha acabado de recorrer el bloque del primer documento
                if (valor2.equals("]")) {
                    do {
                        fWriter.write(valor1);
                        valor1 = scFile1.next();
                    } while (!valor1.equals("]"));
                    continue;
                }

                // Comparar elemento por elemento
                integer1 = Integer.parseInt(valor1);
                integer2 = Integer.parseInt(valor2);

            }
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
