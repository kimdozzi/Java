package javaDeepDive.objectSort;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{5,40},{3,50},{1,30},{4,20},{2,10}};

        // 1.
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        // 2.
        Arrays.sort(arr, (o1, o2) -> o1[0]-o2[0]);

        // 3.
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));


        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}
