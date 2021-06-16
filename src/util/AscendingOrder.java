package util;

import java.util.Comparator;

public class AscendingOrder implements Comparator<Integer> {

    public int compare(Integer a, Integer b) {
        return a - b;
    }
}