package Ordenamientos;

import java.util.Scanner;

import java.util.Comparator;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public abstract class OrdenamientoExterno {

    protected static Comparator<Integer> comparator;
    private static int sizeBlock;
    protected static File directorio = new File("./src/files/");

    // Metodos de acceso

    public static void setDirectorio(File dir) {
        if (dir != null)
            directorio = dir;
        else
            directorio = new File("./src/files/");
    }

    protected static void setComparator(Comparator<Integer> order) {
        comparator = order;
    }

    protected static void setSizeBlock(int size) {
        sizeBlock = size;
    }

    // Metodos

    protected static ArrayList<Integer> ordenamientoInterno(ArrayList<Integer> bloque) {
        Quicksort.setComp(comparator);
        Quicksort.sort(bloque, 0, bloque.size() - 1);
        return bloque;
    }

    protected static void lecturaInicial(File origen, File destino1, File destino2) {
        Scanner scFile;
        FileWriter fWriter1, fWriter2, fWriter;
        int contadorAlternador = 0;
        int alternador;
        ArrayList<Integer> bloque = new ArrayList<>();

        try {
            scFile = new Scanner(origen).useDelimiter(",");
            fWriter1 = new FileWriter(destino1);
            fWriter2 = new FileWriter(destino2);

            do {
                alternador = (int) Math.pow(-1, contadorAlternador);

                if (alternador > 0)
                    fWriter = fWriter1;
                else
                    fWriter = fWriter2;

                while (bloque.size() < sizeBlock && scFile.hasNextInt()) {
                    bloque.add(scFile.nextInt());
                }

                if (bloque.size() > 1)
                    bloque = ordenamientoInterno(bloque);

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

    protected static void intercalar(File archivo1, File archivo2, File destino) {

        Scanner scFile1, scFile2;
        Integer integer1, integer2;
        String valor1, valor2;
        FileWriter fWriter;
        valor1 = null;
        valor2 = null;

        try {
            scFile1 = new Scanner(archivo1).useDelimiter(",");
            scFile2 = new Scanner(archivo2).useDelimiter(",");
            fWriter = new FileWriter(destino);

            if (scFile1.hasNext())
                valor1 = scFile1.next();
            if (scFile2.hasNext())
                valor2 = scFile2.next();
            while (scFile1.hasNext() || scFile2.hasNext()) {

                // Si se ha acabado de recorrer el bloque del primer documento
                if (valor1 != null && valor1.equals("]")) {
                    do {
                        fWriter.write(valor2 + ",");
                        valor2 = scFile2.next();
                    } while (!valor2.equals("]"));
                    fWriter.write("],");
                    if (scFile1.hasNext())
                        valor1 = scFile1.next();
                    if (scFile2.hasNext())
                        valor2 = scFile2.next();
                    continue;
                }

                // Si se ha acabado de recorrer el bloque del primer documento
                if (valor2 != null && valor2.equals("]")) {
                    do {
                        fWriter.write(valor1 + ",");
                        valor1 = scFile1.next();
                    } while (!valor1.equals("]"));
                    fWriter.write("],");
                    if (scFile1.hasNext())
                        valor1 = scFile1.next();
                    if (scFile2.hasNext())
                        valor2 = scFile2.next();
                    continue;
                }

                // Comparar elemento por elemento
                integer1 = Integer.parseInt(valor1);
                integer2 = Integer.parseInt(valor2);

                // COMPARAR LOS ELEMENTOS inetger1-integer2 si integer1>integer2 devuelve pos en
                // otro caso negativo
                if (comparator.compare(integer1, integer2) > 0) {
                    fWriter.write(integer2 + ",");
                    valor2 = scFile2.next();

                } else {
                    fWriter.write(integer1 + ",");
                    valor1 = scFile1.next();
                }

            }
            fWriter.close();
            scFile1.close();
            scFile2.close();
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

    protected static void distribuir(File origen, File destino1, File destino2) {
        Scanner scFile;
        FileWriter fWriter1, fWriter2, fWriter;
        int contador = 0;
        int alternador;
        String valor;

        try {
            scFile = new Scanner(origen).useDelimiter(",");
            fWriter1 = new FileWriter(destino1);
            fWriter2 = new FileWriter(destino2);

            do {
                alternador = (int) Math.pow(-1, contador);
                valor = scFile.next();
                if (alternador > 0)
                    fWriter = fWriter1;
                else
                    fWriter = fWriter2;

                while (!valor.equals("]")) {
                    fWriter.write(valor + ",");
                    valor = scFile.next();
                }
                fWriter.write("],");
                contador++;
            } while (scFile.hasNext());
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

    protected static boolean finalizo(File origen) {
        Scanner scFile;
        String valor;
        int contador = 0;

        try {
            scFile = new Scanner(origen).useDelimiter(",");
            do {
                valor = scFile.next();
                if (valor.equals("]"))
                    contador++;
                if (contador > 1)
                    break;
            } while (scFile.hasNext());
            scFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se ha encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en la lectura: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("El scanner se ha cerrado de forma inesperada: " + e.getMessage());
        }
        if (contador == 1)
            return true;
        else
            return false;
    }

    protected static void escrituraFinal(File origen, File destino) {
        Scanner scFile;
        String valor;
        FileWriter fWriter;

        try {
            scFile = new Scanner(origen).useDelimiter(",");
            fWriter = new FileWriter(destino);
            do {
                valor = scFile.next();
                if (valor.equals("]"))
                    break;
                else
                    fWriter.write(valor + ",");
            } while (scFile.hasNext());
            scFile.close();
            fWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se ha encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("No se ha podido escribir: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en la lectura: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("El scanner se ha cerrado de forma inesperada: " + e.getMessage());
        }

    }

    public static boolean revisarArchivo(File origen) {
        Scanner scFile;
        String valor;
        int contador = 0;
        int elementos = 0;

        try {
            scFile = new Scanner(origen).useDelimiter(",");
            do {
                valor = scFile.next();
                if (!valor.matches("[0-9,]*")) {
                    contador++;
                    break;
                } else
                    elementos++;
            } while (scFile.hasNext());
            scFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se ha encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en la lectura: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("El scanner se ha cerrado de forma inesperada: " + e.getMessage());
        }
        if (elementos == 0 || contador >= 1)
            return false;
        else
            return true;
    }
}
