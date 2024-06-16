package algorithm.baekjoon.S1_12101_123더하기2_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, cnt = 0;
    static boolean flag = false;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        backTracking(0, 0);
        if (!flag) {
            System.out.println(-1);
        }
    }

    private static void backTracking(int depth, int sum) {
        if (sum > N) {
            return;
        }
        if (sum == N) {
            cnt++;
            if (cnt == K) {
                flag = true;
                for (int i = 0; i < depth - 1; i++) {
                    System.out.print(arr[i] + "+");
                }
                System.out.println(arr[depth - 1]);
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            arr[depth] = i;
            backTracking(depth + 1, sum + i);
        }
    }
}
// https://www.acmicpc.net/source/48549594