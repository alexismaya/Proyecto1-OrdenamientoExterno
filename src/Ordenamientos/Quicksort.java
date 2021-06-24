package Ordenamientos;

import java.util.ArrayList;
import java.util.Comparator;

import util.*;

public class Quicksort {

    // public static Comparator comp=new DescendingOrder();

    public static Comparator<Integer> comp = new AscendingOrder();

    static int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comp.compare(arr.get(j), pivot) <= 0) {
                i++;
                Utilerias.swap(arr, i, j);

            }
        }

        Utilerias.swap(arr, i + 1, high);
        // int temp = arr[i+1];
        // arr[i+1] = arr[high];
        // arr[high] = temp;
        return i + 1;
    }

    static void sort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

}
