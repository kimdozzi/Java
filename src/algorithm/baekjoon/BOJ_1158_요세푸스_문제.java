package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스_문제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=n; i++) {
            q.add(i);
        }

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            for (int j=1; j<k; j++) {
                q.add(q.poll());
            }
            ans[i] = q.remove();
        }

        System.out.print("<");
        for (int i=0; i<n-1; i++) {
            System.out.print(ans[i] + ", ");
        }
        System.out.print(ans[n-1] + ">");

    }
}
