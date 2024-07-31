package datastructure.List;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] strArr = new Integer[3];

        list.toArray(strArr);

        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<Integer> list1 = List.of(1, 2, 3);
        list1.add(1,5);

        System.out.println(list1.toString());
    }
}
