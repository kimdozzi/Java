package 삼성전자dx특강.day05.그래도수명이절반이되어서는;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int MAX_N = 200_010;
    static final int MAX_K = 200_010;
    static int N, M;
    static int[] arr = new int[MAX_N];
    static int[] K = new int[MAX_K];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for(int i=0; i<MAX_N; i++) {
                arr[i] = 0;
                K[i] = 0;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                K[i] = Integer.parseInt(st.nextToken());
            }

            int lo = -1;
            int hi = 200_001;
            int answer = Integer.MAX_VALUE;
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (solve(mid)) {
                    answer = Math.min(answer, mid);
                    hi = mid;
                } else {
                    lo = mid;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(answer);
            System.out.println(sb);
        }
    }

    static boolean solve(int m) {
        int blockIdx = -1;
        for (int i = 0; i < K.length; i++) {
            for (int j = 0; j < K[i]; j++) {
                blockIdx++;

                if (blockIdx >= N) {
                    return false;
                }
                if (arr[blockIdx] > m) {
                    i--;
                    break;
                }
            }
        }
        return true;
    }
}
