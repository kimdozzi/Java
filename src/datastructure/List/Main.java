package algorithm.samsung.STL.List;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] strArr = new Integer[3];

        list.toArray(strArr);

    }

}
