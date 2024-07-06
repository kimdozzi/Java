package algorithm.dynamicProgramming.G5_12865_평벙한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] knapsack;
    static int MAX_N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        knapsack = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            knapsack[i][0] = Integer.parseInt(st.nextToken());
            knapsack[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        // 1번째 물건부터 N번째 물건까지 모두 탐색
        for (int i = 1; i <= N; i++) {
            // 무게가 1인 경우부터 무게가 K인 경우까지 모두 탐색
            for (int j = 1; j <= K; j++) {
                // 해당 위치에 물건을 넣을 수 없는 경우 j > W
                if (knapsack[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 현재 아이템을 넣기 전까지의 최대 가치와 현재 아이템이 들어왔을 때 최대 가치 중 큰 값을 dp[i][j]에 저장한다.
                    // dp[i-1][j] : 현재 아이템이 들어오기 전 까지의 최대 가치
                    // dp[i-1][j-knapsack[i][0]] : 현재 아이템을 넣기 전(i-1)까지의 가방의 무게(j)에서 들어오게 될 현재 아이템의 무게(knapsack[i][0])를 제외헀을 때의 최대 가치
                    // knapsack[i][1] : 현재 아이템의 가치
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - knapsack[i][0]] + knapsack[i][1]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
