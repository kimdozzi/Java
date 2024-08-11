package algorithm.DP.G5_15989_123더하기4;

import java.util.Scanner;

public class Main {
    static Integer[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = sc.nextInt();
        }
        dp = new Integer[4][10001];

        for (int i = 0; i <= 10000; i++) {
            dp[0][i] = 0;
        }

        dp[0][0] = 1;

        for(int i=0; i<arr.length; i++) {
            System.out.println(top_down(3, arr[i]));
        }
    }

    private static int top_down(int n, int v) {
        if (dp[n][v] == null) {
            if (n > v) {
                dp[n][v] = top_down(n - 1, v);
            } else {
                dp[n][v] = top_down(n - 1, v) + top_down(n, v - n);
            }
        }
        return dp[n][v];
    }
}