package algorithm.dynamicProgramming.G5_9084_동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                int coin = coins[i];
                for (int j = coin; j <= M; j++) {
                    dp[j] += dp[j - coin];
                }
            }
            System.out.println(dp[M]);
        }
    }
}
