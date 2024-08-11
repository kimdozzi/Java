package algorithm.Graph.G4_1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static List<Edge>[] graph;
    static int n, m;
    static int[] d;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        d = new int[n + 1];
        check = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            d[i] = Integer.MAX_VALUE;
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
        }

        solve(start);
        for(int i=1; i<=n; i++) {
            System.out.println(d[i] == Integer.MAX_VALUE ? "INF" : d[i]);
        }
    }

    private static void solve(int start) {
        Queue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (check[edge.to]) continue;
            check[edge.to] = true;

            for(Edge e : graph[edge.to]) {
                if (d[e.to] > d[edge.to] + e.weight) {
                    d[e.to] = d[edge.to] + e.weight;
                    pq.add(new Edge(e.to, d[e.to]));
                }
            }
        }

    }

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o1) {
            return this.weight - o1.weight;
        }
    }
}
