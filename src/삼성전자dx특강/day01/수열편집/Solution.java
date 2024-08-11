package 삼성전자dx특강.day01.수열편집;

import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

    private static final LinkedList<Long> ll = new LinkedList<>();
    private static int N, M, L;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            L = sc.nextInt();

            ll.clear();

            for (int i = 0; i < N; i++) {
                ll.add(sc.nextLong());
            }
            sc.nextLine();

            for (int j = 0; j < M; j++) {
                String[] line = sc.nextLine().split(" ");

                switch (line[0]) {
                    case "I":
                        ll.add(Integer.parseInt(line[1]), Long.parseLong(line[2]));
                        break;

                    case "D":
                        ll.remove(Integer.parseInt(line[1]));
                        break;

                    case "C":
                        ll.set(Integer.parseInt(line[1]), Long.parseLong(line[2]));
                        break;

                    default:
                        break;
                }
            }

            if (ll.size() <= L)
                System.out.printf("#%d %d\n", test_case, -1);
            else
                System.out.printf("#%d %d\n", test_case, ll.get(L));
        }
    }
}