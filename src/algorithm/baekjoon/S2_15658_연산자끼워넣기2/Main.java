package algorithm.baekjoon.S2_15658_연산자끼워넣기2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr, oper = new int[4];
    static int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // init
        input();

        //solve
        solve(1, arr[0]);

        System.out.print(mx + "\n" + mn);
    }

    private static void solve(int index, int sum) {
        if (index == n) {
            mx = Math.max(mx, sum);
            mn = Math.min(mn, sum);
            return;
        }

        for (int op = 0; op < 4; op++) {
            if (oper[op] > 0) {
                oper[op]--;
                solve(index + 1, compute(sum, op, arr[index]));
                oper[op]++;
            }
        }
    }

    private static int compute(int a, int op, int b) {
        switch (op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
        }
        return -1;
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
    }
}
