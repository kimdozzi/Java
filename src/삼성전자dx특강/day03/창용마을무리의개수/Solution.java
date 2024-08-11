package 삼성전자dx특강.day03.창용마을무리의개수;

import java.util.HashSet;
import java.util.Scanner;

class Solution {
    static int[] parent = new int[101];
    static int[] rank = new int[101];
    static int N, M;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            for (int i = 0; i <= 100; i++) {
                parent[i] = 0;
                rank[i] = 0;
            }

            N = sc.nextInt();
            M = sc.nextInt();

            for (int i = 0; i <= N; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            for (int i = 0; i < M; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                union(x, y);
            }
            for (int i = 1; i <= N; i++) {
                find(i);
            }
            HashSet<Integer> set = new HashSet<>();

            for (int i = 1; i <= N; i++) {
                if (!set.contains(parent[i])) {
                    set.add(parent[i]);
                }
            }
            System.out.printf("#%d %d\n ", test_case, set.size());
            //System.ou.println(Arrays.toString(parent));
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[x] = y;
            rank[y]++;
        }
        return true;
    }
}
