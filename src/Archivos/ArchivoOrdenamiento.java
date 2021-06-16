package Archivos;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArchivoOrdenamiento extends Archivador {

    private Scanner sc;
    // blocSize es el tamaño del bloque que se debe generar
    private int blockSize;
    // readBlock se refiere hasta el bloque que se ha leido
    private int readBlock = 0;

    // Constructor

    public ArchivoOrdenamiento(String nombreArchivo) {
        super(nombreArchivo);
        blockSize = 10;
    }

    // Setters y getters

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    // Metodo para leer bloques

    public ArrayList<Integer> generarBloque() {
        ArrayList<Integer> bloque = new ArrayList<Integer>();
        int elementoActual = 0;

        try {
            sc = new Scanner(archivo).useDelimiter(",");
            while (sc.hasNextLine() && bloque.size() < blockSize) {
                elementoActual++;
                if (elementoActual > blockSize * readBlock) {
                    bloque.add(sc.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + getNombreArchivo() + " no se ha encontrado.");
        } catch (InputMismatchException e) {
            System.out.println("Error en la lectura: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("El scanner se ha cerrado de forma inesperada: " + e.getMessage());
        }

        return bloque;
    }

}
