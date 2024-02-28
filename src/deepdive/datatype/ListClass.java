package deepdive.datatype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListClass {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        // add
        list1.add("one");
        list1.add(0,"zero");
        list2.add("two");
        list2.add("zero");

        // merge list
        list1.addAll(list2);

        // find index
        list1.indexOf("zero");
        list1.lastIndexOf("zero");

        // remove
        list1.remove(3);

        // 리스트 차집합
        list1.removeAll(list2);

        // 리스트 교집합
        list1.retainAll(list2);

        // clear
        list1.clear();

        // size
        list1.size();

        // isEmpty
        list1.isEmpty();

        // contains
        list1.contains("two");
        list1.containsAll(list2);

        //------------------------------------
        List<Integer> arr = new ArrayList<>(
                Arrays.asList(1,2,3)
        );

        arr.forEach(System.out::println);
        arr.removeIf(x -> x % 2 != 0);

        arr.forEach(System.out::println);

        // https://earthteacher.tistory.com/169#gsc.tab=0
    }
}
