package algorithm.graph.G4_1504_특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int to, weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

public class Main {
    static int v, e, x, y;
    static List<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static final int INF = 200000000;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // init
        init();

        // solve
        int res1 = dijkstra(1, x) + dijkstra(x, y) + dijkstra(y, v);
        int res2 = dijkstra(1, y) + dijkstra(y, x) + dijkstra(x, v);
        int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

        System.out.println(ans);
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(visited, false);
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int cur = pq.poll().to;

            if(visited[cur]) continue;
            visited[cur] = true;

            for(Node node : graph[cur]) {
                if (!visited[node.to] && dist[cur] + node.weight < dist[node.to]) {
                    dist[node.to] = dist[cur] + node.weight;
                    pq.add(new Node(node.to, dist[node.to]));
                }
            }
        }

        return dist[end];
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new List[v + 1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[v + 1];
        dist = new int[v + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
    }
}
