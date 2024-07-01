package algorithm.baekjoon.G5_25391_특별상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparing((int[] o) -> o[1]).reversed());

        long max = 0;
        for (int i = 0; i < K; i++) {
            max += arr[i][0];
        }

        int[][] remained = new int[N - K][2];
        for (int i = 0; i < N - K; i++) {
            remained[i][0] = arr[i + K][0];
            remained[i][1] = arr[i + K][1];
        }

        Arrays.sort(remained, Comparator.comparing((int[] o) -> o[0]).reversed());

        for (int i = 0; i < M; i++) {
            max += remained[i][0];
        }
        System.out.println(max);

    }
}
