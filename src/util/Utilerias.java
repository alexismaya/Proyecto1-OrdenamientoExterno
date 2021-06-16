package util;

import java.util.ArrayList;

public class Utilerias {

    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void swap(ArrayList<Integer> arr, int i, int j) {
        // int temp = arr[i];
        // arr[i] = arr[j];
        // arr[j] = temp;

        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

}
