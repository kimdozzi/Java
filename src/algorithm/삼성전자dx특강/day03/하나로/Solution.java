package algorithm.삼성전자dx특강.day03.하나로;

import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    static int[] parent;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[] x = new int[N];
            int[] y = new int[N];

            for (int i = 0; i < N; i++) {
                x[i] = sc.nextInt();
            }
            for (int i = 0; i < N; i++) {
                y[i] = sc.nextInt();
            }
            double E = sc.nextDouble();

            // 모든 정점을 순회하면서 한 정점에서 이동 가능한 모든 정점을 pq에 추가.
            PriorityQueue<Edge> pq = getEdges(N, x, y, E);

            // parent 배열 초기화.
            initParent(N);

            // 비용 계산.
            double res = getResult(pq, N);
            System.out.printf("# %d %.0f\n", test_case, res);
        }
    }

    private static double getResult(PriorityQueue<Edge> pq, int N) {
        double res = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.start, edge.end)) {
                if (cnt == N - 1) {
                    break;
                }
                res += edge.cost;
                cnt++;
            }
        }
        return res;
    }

    private static void initParent(int N) {
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static PriorityQueue<Edge> getEdges(int N, int[] x, int[] y, double E) {
        PriorityQueue<Edge> tempPq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
                tempPq.add(new Edge(i, j, E * dist));
            }
        }
        return tempPq;
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }
        if (x > y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
        return true;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        double cost;

        Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}