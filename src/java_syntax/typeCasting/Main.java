package java_syntax.typeCasting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        List<Integer> -> int[] array

        // 방법 1.
        List<Integer> collect = new ArrayList<>();
        int[] array = collect.stream().mapToInt(i -> i).toArray();

        // 방법 2.
        int[] array1 = collect.stream().mapToInt(Integer::intValue).toArray();

        // 방법 3.
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1); integerList.add(2); integerList.add(3);

        int[] intArray = integerList.stream().mapToInt(Integer::intValue).toArray();


//        int[] array -> List<Integer>

        int[] num = {1,3,5,7,4};
        List<Integer> integerListToIntArray = Arrays.stream(num).boxed().collect(Collectors.toList());


        // boxed()는 IntStream과 같이 원시 타입에 대한 스트림 지원을 클래스 타입으로 전환해줌
        // int[] -> IntStream -> Stream<Integer> -> Integer[]

        IntStream intStream = Arrays.stream(num);
        Stream<Integer> boxed = intStream.boxed();
        Integer[] result = boxed.toArray(Integer[]::new);
        System.out.println(Arrays.toString(result));

        // one line

        Integer[] oneLineResult = Arrays.stream(num).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(oneLineResult));



    }
}
