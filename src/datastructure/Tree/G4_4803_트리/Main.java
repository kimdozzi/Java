package algorithm.tree.G4_4803_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent = new int[501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            init();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                union(x, y);
            }

            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                int tmp = find(parent[i]);
                if (tmp > 0 && !set.contains(tmp)) {
                    set.add(tmp);
                }
            }

            int cnt = set.size();
            if (cnt == 0) {
                sb.append("Case ").append(idx).append(": No trees.").append("\n");
            } else if (cnt == 1) {
                sb.append("Case ").append(idx).append(": There is one tree.").append("\n");
            } else {
                sb.append("Case ").append(idx).append(": A forest of ").append(cnt).append(" trees.").append("\n");
            }
            idx++;
        }
        System.out.println(sb);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            parent[x] = 0;
            parent[y] = 0;
            return;
        }
        if (parent[x] < parent[y]) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    private static void init() {
        Arrays.fill(parent, 0);

        for (int i = 1; i <= N; i++) {
            parent[i] = i; // 루트노드 자기 자신을 ㅗ초기화
        }
    }
}
