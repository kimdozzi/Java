package algorithm.baekjoon.S3_10972_다음순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        // init
        input();

        // solve
        nextPermutation();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void nextPermutation() {
        int flagIdx = N - 1;
        // 1. 오른쪽 맨 뒤에서 부터, Ai-1 < Ai인 수 찾기
        while (flagIdx > 0 && arr[flagIdx - 1] > arr[flagIdx]) {
            flagIdx--;
        }

        if (flagIdx == 0) {
            System.out.print(-1);
            return;
        }

        // 2. Ai-1 과 Ai 를 기준으로 left, right 공간 나눠서 생각하고,
        // 3. left의 맨 오른쪽 인덱스(flagIdx)의 수를 기준으로, right의 맨 오른쪽에서 왼쪽으로 탐색했을 때 큰 수를 찾는다.
        int rightMaxIdx = N - 1;
        while (rightMaxIdx > flagIdx && arr[flagIdx - 1] > arr[rightMaxIdx]) {
            rightMaxIdx--;
        }

        int temp = arr[flagIdx - 1];
        arr[flagIdx - 1] = arr[rightMaxIdx];
        arr[rightMaxIdx] = temp;

        Arrays.sort(arr, flagIdx, N);

        for (int i = 0; i < N - 1; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append(arr[N - 1]);

        System.out.println(sb);
    }
}
