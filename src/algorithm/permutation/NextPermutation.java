package algorithm.permutation;

import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {
    static int[] number;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        N = line.length();
        number = new int[N];

        for (int i=0; i<line.length(); i++) {
            number[i] = line.charAt(i) - '0';
        }

        nextPermutation();
    }

    private static void nextPermutation() {
        int idx = N-1;
        while (idx > 0 && number[idx - 1] > number[idx]) {
            idx--;
        }

        if (idx == 0) {
            System.out.println("last permutation.");
            return;
        }

        int rightMaxIdx = N - 1;
        while (rightMaxIdx > idx && number[idx - 1] > number[rightMaxIdx]) {
            rightMaxIdx--;
        }

        int temp = number[idx-1];
        number[idx - 1] = number[rightMaxIdx];
        number[rightMaxIdx] = temp;

        Arrays.sort(number, idx, N);

        for (int i=0; i< number.length; i++) {
            sb.append(number[i]);
        }
        System.out.println(sb);
    }
}
