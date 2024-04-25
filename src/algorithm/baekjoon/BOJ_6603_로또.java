package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_6603_로또 {
    private static int[] arr;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;

            arr = new int[k];

            for (int i=0; i<k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backTracking(0, new ArrayList<>());
            System.out.println();
        }

    }

    private static void backTracking(int idx, List<Integer> temp) {
        if (idx >= 6) {
            print(temp);
            return;
        }

        for (int i=idx; i<k; i++) {
            if (!temp.isEmpty() && temp.get(idx-1) >= arr[i]) continue;
            temp.add(arr[i]);
            backTracking(idx+1, temp);
            temp.remove(idx);
        }
    }

    private static void print(List<Integer> temp) {
        for (int i=0; i<6; i++) {
            System.out.print(temp.get(i) + " ");
        }
        System.out.println();
    }
}
