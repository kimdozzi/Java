package algorithm.baekjoon.S4_1459걷기_Greedy;

import java.util.Scanner;

public class Main {
    static long x = 0, y = 0;
    static long ny, nx, w, s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ny = sc.nextInt();
        nx = sc.nextInt();
        w = sc.nextInt();
        s = sc.nextInt();

        long minDist = Math.min(nx,ny);
        long maxDist = Math.max(nx,ny);
        long totalDist = nx + ny;

        // 평행이동만 하는 경우 (nx+ny) * w
        long case01 = totalDist * w;

        // 대각선으로만 이동하는 경우 (짝수는 max(nx,ny)*s, 홀수는 (max(nx,ny)-1)*s+w
        long case02 = ((totalDist & 1) == 0) ? maxDist * s : (maxDist - 1) * s + w;

        //  (n < m)일때, 대각선으로 (n,n)까지 가서 (n,m) or (m,n)으로 가는 경우
        long case03 = (minDist * s) + ((maxDist - minDist) * w);

        System.out.println(Math.min(case01, Math.min(case02, case03)));
    }
}
