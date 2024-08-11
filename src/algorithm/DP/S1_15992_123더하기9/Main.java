package algorithm.dynamicProgramming.S1_15992_123더하기9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static long[][] dp = new long[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 2;
        dp[3][3] = 1;

        for (int i = 4; i <= 1000; i++) {
            for (int j = 2; j < i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 2][j - 1] + dp[i - 3][j - 1]) % 1_000_000_009;
            }
            dp[i][i] = 1;
        }

        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            long sum = 0;
            for (int j = 1; j <= M; j++) {
                sum = (sum + dp[N][j]) % 1_000_000_009;
            }
            System.out.println(sum);
        }
    }
}