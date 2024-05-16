package algorithm;
import java.util.*;
import java.util.stream.Stream;

public class sort {
    public static void main(String[] args) {
        /*
        * Arrays.sort (arr, 시작 Index, 끝 Index + 1)
        * */
        // 숫자
        // 오름차순
        int[] arr = new int[]{12, 41, 37, 81, 19, 25, 60, 20};
        Arrays.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);

        // 내림차순
        // Java에서는 Primitive type으로 구성된 배열을 한번에 내림차순 불가능

        // 방법 1. Integer로 배열을 선언하고, Collections 클래스를 사용한다.
        Integer[] integerArr = new Integer[]{12, 41, 37, 81, 19, 25, 60, 20};
        Arrays.sort(integerArr, Collections.reverseOrder());
        Arrays.stream(integerArr).forEach(System.out::print);
        System.out.println();

        // 방법 2. Stream을 이용하여 int[] -> Integer[]로 변환 후, Collections 클래스 사용
        Integer[] integerArr02 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(integerArr02, Collections.reverseOrder());
        Arrays.stream(integerArr02).forEach(System.out::print);
        System.out.println();

        // 문자열 내 문자 정렬 (char)
        // 오름차순
        String str = "bcade";
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sortedStr = new String(chars);
        System.out.println(sortedStr);

        // 내림차순
        char[] chars1 = str.toCharArray();
        Arrays.sort(chars1);
        StringBuilder stringBuilder = new StringBuilder(new String(chars1));
        StringBuilder reversedStr = stringBuilder.reverse();
        System.out.println(reversedStr);

    }
}
