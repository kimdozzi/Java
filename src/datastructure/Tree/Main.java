package datastructure.Tree;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        ts.put(1, 5);
        ts.put(2, 4);
        ts.put(3, 3);
        ts.put(5, 1);

        Entry<Integer, Integer> integerIntegerEntry = ts.firstEntry();
        System.out.println(integerIntegerEntry.getKey() + " " + integerIntegerEntry.getValue());

        Entry<Integer, Integer> integerIntegerEntry1 = ts.lastEntry();
        System.out.println(integerIntegerEntry1.getKey() + " " + integerIntegerEntry1.getValue());
    }
}
