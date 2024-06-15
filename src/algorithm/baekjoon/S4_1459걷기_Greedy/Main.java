package algorithm.baekjoon.S4_1459걷기_Greedy;

import java.util.Scanner;

public class Main {
    static int x = 0, y = 0;
    static int ny, nx, w, s;
    static long cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ny = sc.nextInt();
        nx = sc.nextInt();
        w = sc.nextInt();
        s = sc.nextInt();

//        int idx = 5;
        while (true) {
            if (x == nx && y == ny) {
                break;
            }
            if (nx > x && ny > y) {
                cnt += Math.min(w * 2, s);
                x++;
                y++;
            } else if (nx > x + 1) {
                cnt += Math.min(w * 2, s * 2);
                x += 2;
            } else if (ny > y + 1) {
                cnt += Math.min(w * 2, s * 2);
                y += 2;
            } else if (nx > x && ny == y) {
                cnt += w;
                x++;
            } else if (ny > y && nx == x) {
                cnt += w;
                y++;
            }
//            System.out.println(x + " " + y + "\n" + nx + " " + ny);
        }
        System.out.println(cnt);
    }
}
