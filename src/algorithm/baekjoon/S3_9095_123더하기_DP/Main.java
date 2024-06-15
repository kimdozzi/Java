package algorithm.baekjoon.S3_9095_123더하기_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        dp = new int[500];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < 500; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }

    private static int rec_func(int sum, int goal) {
        if (sum > goal) {
            return 0;
        }
        if (sum == goal) {
            return 1;
        }
        int now = 0;
        for (int i = 1; i <= 3; i++) {
            now += rec_func(sum + i, goal);
        }
        return now;
    }
}