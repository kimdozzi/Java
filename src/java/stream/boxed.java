package java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class boxed {

    public static void main(String[] args) {
        /*
         * boxed()는 IntStream과 같이 원시 타입에 대한 스트림 지원을 클래스 타입으로 전환해줌
         * */

        int[] num = {1,3,5,7,4};

        // int[] -> IntStream -> Stream<Integer> -> Integer[]
        IntStream intStream = Arrays.stream(num);
        Stream<Integer> boxed = intStream.boxed();
        Integer[] result = boxed.toArray(Integer[]::new);
        System.out.println(Arrays.toString(result));

        // one line
        Integer[] oneLineResult = Arrays.stream(num).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(oneLineResult));

        // int[] -> List<Integer>
        List<Integer> collect = Arrays.stream(num).boxed().collect(Collectors.toList());
        System.out.println(collect);

        // List<Integer> -> int[]
        // 방법 1.
        int[] array = collect.stream().mapToInt(i -> i).toArray();

        // 방법 2.
        int[] array1 = collect.stream().mapToInt(Integer::intValue).toArray();

    }
}
