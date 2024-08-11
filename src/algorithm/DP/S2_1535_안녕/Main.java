package algorithm.DP.S2_1535_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] people;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N][2];
        dp = new int[N][101];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int life = people[i][0];
            int happy = people[i][1];

            for (int j = 0; j < 100; j++) {
                if (i == 0) {
                    if (j >= life) {
                        dp[i][j] = happy;
                    }
                } else {
                    if (j >= life) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - life] + happy);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        System.out.print(dp[N - 1][99]);
    }
}
