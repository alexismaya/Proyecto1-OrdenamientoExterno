package util;

import java.util.Comparator;

public class DescendingOrder implements Comparator<Integer> {

    public int compare(Integer a, Integer b) {
        return b - a;
    }

}
