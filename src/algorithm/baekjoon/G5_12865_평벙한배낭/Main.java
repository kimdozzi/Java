package algorithm.baekjoon.G5_12865_평벙한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static Integer[][] dp;
    private static int[][] stuff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new Integer[N + 1][K + 1];
        stuff = new int[N][2];

        for (int i = 0; i < stuff.length; i++) {
            st = new StringTokenizer(br.readLine());
            stuff[i][0] = Integer.parseInt(st.nextToken());
            stuff[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(topDown(N - 1, K));
    }

    private static int topDown(int n, int k) {
        if (n < 0) {
            return 0;
        }

        int curWeight = stuff[n][0];
        int curValue = stuff[n][1];

        if (dp[n][k] == null) {
            if (curWeight > k) {
                dp[n][k] = topDown(n - 1, k);
            } else {
                dp[n][k] = Math.max(topDown(n - 1, k), topDown(n - 1, k - curWeight) + curValue);
            }
        }
        return dp[n][k];
    }
}
